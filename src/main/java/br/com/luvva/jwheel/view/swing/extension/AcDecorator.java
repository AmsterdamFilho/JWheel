package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.control.autocomplete.AcController;
import br.com.luvva.jwheel.control.autocomplete.AcListener;
import br.com.luvva.jwheel.view.swing.providers.SwImageProvider;
import br.com.luvva.jwheel.view.swing.utils.JTableNavigator;
import br.com.luvva.jwheel.view.swing.utils.TextChangedListener;
import br.com.luvva.jwheel.view.utils.RecordViewNavigator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AcDecorator implements KeyListener, PropertyChangeListener, FocusListener, AcListener
{
    private JTextComponent textComponent;

    // settings
    private boolean            selectFirstWhenPopupShows = true;
    private boolean            showAutoCompleteButton    = true;
    private int                maxVisibleRows            = 5;
    private float              popupSizeFactor           = 1;
    private HintProvider       hintProvider              = null;
    private FocusGainedHandler focusGainedHandler        = null;
    /**
     * Time after which the popup should close when new auto complete request is taking too long
     */
    private int                autoCompleteTimeLimit     = 1500;
    /**
     * The general time that it takes for a keyboard user to type one key after the other (around 300ms)
     * plus some tolerance (100) to check is the user really has finished typing.
     */
    private int                keyEventDelay             = 400;

    private         AcController    controller;
    private @Inject SwImageProvider swImageProvider;

    // components
    private AcButton       button       = new AcButton();
    private AcButtonBorder buttonBorder = new AcButtonBorder();
    private AcPopupMenu    popup        = new AcPopupMenu();

    // helpers
    private boolean          textIsChanging      = false;
    private boolean          textIsMatching      = false;
    private DocumentListener textChangedListener = new TextChangedListener()
    {
        @Override
        public void textChanged (DocumentEvent de)
        {
            if (!textIsMatching)
            {
                textIsChanging = true;
                controller.autoCompleteAccepted(null);
                autoComplete();
                textIsChanging = false;
            }
        }
    };
    private LayoutManager textFieldOriginalLayout;
    private Border        textFieldOriginalBorder;
    private AcSwingWorker autoCompleteSw;

    //<editor-fold desc="Settings">

    public void setSelectFirstWhenPopupShows (boolean selectFirstWhenPopupShows)
    {
        this.selectFirstWhenPopupShows = selectFirstWhenPopupShows;
    }

    public void setShowAutoCompleteButton (boolean showAutoCompleteButton)
    {
        this.showAutoCompleteButton = showAutoCompleteButton;
    }

    public void setMaxVisibleRows (int maxVisibleRows)
    {
        this.maxVisibleRows = maxVisibleRows;
    }

    public void setPopupSizeFactor (float popupSizeFactor)
    {
        this.popupSizeFactor = popupSizeFactor;
    }

    public void setKeyEventDelay (int keyEventDelay)
    {
        this.keyEventDelay = keyEventDelay;
    }

    public void setHintProvider (HintProvider hintProvider)
    {
        this.hintProvider = hintProvider;
    }

    public void setFocusGainedHandler (FocusGainedHandler focusGainedHandler)
    {
        this.focusGainedHandler = focusGainedHandler;
    }

    public void setController (AcController controller)
    {
        setController(controller, null);
    }

    public void setController (AcController controller, JwTable.Configurator configurator)
    {
        if (textComponent == null)
        {
            this.controller = controller;
            popup.configureTable(configurator);
        }
        else
        {
            throw new IllegalStateException("Can not set a new controller when " +
                    "a text component has already been decorated! Undo must be called first.");
        }
    }

    //</editor-fold>

    @PostConstruct
    private void init ()
    {
        button.setIcon(swImageProvider.acButtonIcon());
    }

    public void decorate (JTextComponent textComponent)
    {
        if (this.textComponent == textComponent || textComponent == null)
        {
            return;
        }
        if (this.textComponent != null)
        {
            throw new IllegalStateException("Can't be decorating more than one component at the same time!");
        }
        if (controller == null)
        {
            throw new IllegalStateException("Can't decorate if a controller has not been set!");
        }

        this.textComponent = textComponent;
        configureAccordingToTextComponent();
        textFieldOriginalLayout = textComponent.getLayout();
        textFieldOriginalBorder = textComponent.getBorder();

        textComponent.addPropertyChangeListener(this);
        textComponent.addFocusListener(this);
        textComponent.addKeyListener(this);
        textComponent.getDocument().addDocumentListener(textChangedListener);
        textComponent.setLayout(null);
        textComponent.setBorder(new CompoundBorder(textComponent.getBorder(), buttonBorder));
        textComponent.add(button);

        controller.addListener(this);

        configureButton();

        matchTextToSelectedAutoCompleteOption();
    }

    public void undo ()
    {
        if (textComponent != null)
        {
            textComponent.removePropertyChangeListener(this);
            textComponent.removeFocusListener(this);
            textComponent.removeKeyListener(this);
            textComponent.getDocument().removeDocumentListener(textChangedListener);
            textComponent.setLayout(textFieldOriginalLayout);
            textComponent.setBorder(textFieldOriginalBorder);
            textComponent.remove(button);
            textComponent.setText("");

            controller.removeListener(this);

            popup.setAutoCompleteOptions(new ArrayList<>());

            textComponent = null;
            hintProvider = null;
            focusGainedHandler = null;
        }
    }

    private void configureAccordingToTextComponent ()
    {
        if (hintProvider == null)
        {
            if (textComponent instanceof JwPasswordField)
            {
                JwPasswordField jwPasswordField = (JwPasswordField) textComponent;
                hintProvider = source -> jwPasswordField.getStringPassword();
            }
            else
            {
                hintProvider = JTextComponent::getText;
            }
        }
        if (focusGainedHandler == null)
        {
            if (textComponent instanceof JTextArea || textComponent instanceof JEditorPane)
            {
                focusGainedHandler = source -> {
                };
            }
            else
            {
                focusGainedHandler = JTextComponent::selectAll;
            }
        }
    }

    private void configureButton ()
    {
        boolean buttonShouldShow = true;
        if (!(textComponent.isEnabled() && textComponent.isEditable() && showAutoCompleteButton))
        {
            buttonShouldShow = false;
        }
        button.setVisible(buttonShouldShow);
        buttonBorder.configureMargin();
    }

    private void autoCompleteOptionAcceptedFromView ()
    {
        controller.autoCompleteAccepted(popup.getSelectedOption());
        closePopup();
    }

    private void closePopup ()
    {
        popup.setVisible(false);
    }

    private void showAutoCompleteOptions (List<Object[]> options)
    {
        popup.setAutoCompleteOptions(options);
        popup.configureSize((int) (textComponent.getWidth() * popupSizeFactor), maxVisibleRows);
        popup.show(textComponent, 0, textComponent.getHeight());
    }

    @Override
    public void keyTyped (KeyEvent keyEvent)
    {
    }

    @Override
    public void keyPressed (KeyEvent keyEvent)
    {
        if (popup.isShowing())
        {
            switch (keyEvent.getKeyCode())
            {
                case KeyEvent.VK_PAGE_UP:
                    popup.previousPage();
                    keyEvent.consume();
                    break;
                case KeyEvent.VK_UP:
                    popup.previous();
                    keyEvent.consume();
                    break;
                case KeyEvent.VK_PAGE_DOWN:
                    popup.nextPage();
                    keyEvent.consume();
                    break;
                case KeyEvent.VK_DOWN:
                    popup.next();
                    keyEvent.consume();
                    break;
                default:
            }
        }
    }

    @Override
    public void keyReleased (KeyEvent evt)
    {
        switch (evt.getKeyCode())
        {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
                if (!popup.isShowing())
                {
                    autoComplete();
                    evt.consume();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (popup.isShowing())
                {
                    closePopup();
                    evt.consume();
                }
                break;
            case KeyEvent.VK_ENTER:
                if (popup.isShowing() && popup.hasSelectedOption())
                {
                    autoCompleteOptionAcceptedFromView();
                    evt.consume();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void focusGained (FocusEvent e)
    {
        focusGainedHandler.focusGained(textComponent);
    }

    @Override
    public void focusLost (FocusEvent e)
    {
        if (popup.isShowing())
        {
            if (popup.hasSelectedOption())
            {
                autoCompleteOptionAcceptedFromView();
            }
            else
            {
                closePopup();
            }
        }
    }

    @Override
    public void propertyChange (PropertyChangeEvent evt)
    {
        switch (evt.getPropertyName())
        {
            case "editable":
            case "enable":
                configureButton();
        }
    }

    @Override
    public void autoCompleteOptionAccepted ()
    {
        if (!textIsChanging)
        {
            matchTextToSelectedAutoCompleteOption();
        }
    }

    private void matchTextToSelectedAutoCompleteOption ()
    {
        textIsMatching = true;
        textComponent.setText(controller.getSelectedAsString());
        textIsMatching = false;
    }

    private void autoComplete ()
    {
        if (autoCompleteSw != null)
        {
            if (!autoCompleteSw.isDone())
            {
                autoCompleteSw.cancel(true);
            }
        }
        autoCompleteSw = new AcSwingWorker(hintProvider.getHint(textComponent));
        autoCompleteSw.execute();
        new AcTimer(e -> acTimerExpired(autoCompleteSw)).start();
    }

    private void acTimerExpired (AcSwingWorker swingWorker)
    {
        if (!(swingWorker.isDone() || swingWorker.isCancelled()))
        {
            closePopup();
        }
    }

    private class AcTimer extends Timer
    {
        private AcTimer (ActionListener actionListener)
        {
            super(autoCompleteTimeLimit, actionListener);
            setRepeats(false);
        }
    }

    private class AcSwingWorker extends SwingWorker<List<Object[]>, Void>
    {

        private String hint;

        private AcSwingWorker (String hint)
        {
            this.hint = hint;
        }

        @Override
        protected List<Object[]> doInBackground () throws Exception
        {
            if (controller.autocompleteIsResourceConsuming(hint))
            {
                Thread.sleep(keyEventDelay);
            }
            if (isCancelled())
            {
                return new ArrayList<>();
            }
            return controller.getAutoCompleteOptions(hint);
        }

        @Override
        protected void done ()
        {
            if (isCancelled())
            {
                return;
            }
            List<Object[]> results = null;
            try
            {
                results = get();
            }
            catch (InterruptedException | ExecutionException | CancellationException ignored)
            {
            }
            if (results != null && textComponent.isFocusOwner())
            {
                if (results.isEmpty())
                {
                    popup.setAutoCompleteOptions(results);
                    closePopup();
                }
                else
                {
                    showAutoCompleteOptions(results);
                    if (selectFirstWhenPopupShows)
                    {
                        popup.first();
                    }
                }
            }
        }

    }

    private class AcPopupMenu extends JPopupMenu implements RecordViewNavigator
    {
        private JwTableModel    tableModel   = new JwTableModel();
        private JwTable         table        = new JwTable(tableModel);
        private JTableNavigator navigator    = new JTableNavigator(table);
        private JwScrollPane    jwScrollPane = new JwScrollPane(table);

        private int tableHeaderHeight;
        private int scrollTopAndBottomInsetsSum;

        private AcPopupMenu ()
        {
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(0, 0, 0, 0));
            setFocusable(false);

            table.setFocusable(false);
            table.setBorder(null);

            table.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked (MouseEvent e)
                {
                    autoCompleteOptionAcceptedFromView();
                }
            });

            jwScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            add(jwScrollPane, BorderLayout.CENTER);
        }

        private void setAutoCompleteOptions (List<Object[]> options)
        {
            tableModel.setData(options);
        }

        private Object[] getSelectedOption ()
        {
            return table.getSelectedRowData();
        }

        private boolean hasSelectedOption ()
        {
            return table.getSelectedRow() >= 0;
        }

        private void configureSize (int width, int maxVisibleRows)
        {
            int rowCount = table.getRowCount();
            int visibleRowsCount = rowCount > maxVisibleRows ? maxVisibleRows : rowCount;
            int visibleRowsHeight = table.getRowHeight() * visibleRowsCount;
            int tableVisibleHeight = visibleRowsHeight + tableHeaderHeight;
            setPopupSize(width, tableVisibleHeight + scrollTopAndBottomInsetsSum);
        }

        private void configureTable (JwTable.Configurator configurator)
        {
            table.configure(configurator);
            JTableHeader header = table.getTableHeader();
            // ideally, should use header.getHeight(). But that returns 0 until the table is visible for the first time.
            tableHeaderHeight = header == null ? 0 : header.getPreferredSize().height;
            Insets borderInsets = jwScrollPane.getBorder().getBorderInsets(jwScrollPane);
            scrollTopAndBottomInsetsSum = borderInsets.bottom + borderInsets.top;
        }

        @Override
        public void first ()
        {
            navigator.first();
        }

        @Override
        public void previousPage ()
        {
            navigator.previousPage();
        }

        @Override
        public void previous ()
        {
            navigator.previous();
        }

        @Override
        public void next ()
        {
            navigator.next();
        }

        @Override
        public void nextPage ()
        {
            navigator.nextPage();
        }

        @Override
        public void last ()
        {
            navigator.last();
        }

    }

    private class AcButton extends AlphaButton
    {
        private AcButton ()
        {
            setAlignmentX(JComponent.RIGHT_ALIGNMENT);
            setAlignmentY(JComponent.CENTER_ALIGNMENT);
            setFocusable(false);
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            setAlpha(0.5f);
            setMinimumSize(getFixedSize());
            setMaximumSize(getFixedSize());
            setPreferredSize(getFixedSize());
            setSize(getFixedSize());
            addActionListener(e -> {
                textComponent.requestFocusInWindow();
                autoComplete();
            });
        }

        protected Dimension getFixedSize ()
        {
            return new Dimension(20, 15);
        }
    }

    private class AcButtonBorder implements Border
    {
        private final Insets borderInsets = new Insets(0, 0, 0, 0);
        private final int    rightGap     = 6;

        @Override
        public void paintBorder (Component c, Graphics g, int x, int y, int width, int height)
        {
            if (button.isVisible())
            {
                float xCoordinate = rightGap / 2 + (width - rightGap - button.getWidth());
                float yCoordinate = (height - button.getHeight()) / 2;
                button.setLocation((int) xCoordinate + x, (int) yCoordinate + y);
            }
        }

        @Override
        public Insets getBorderInsets (Component c)
        {
            return borderInsets;
        }

        @Override
        public boolean isBorderOpaque ()
        {
            return false;
        }

        private void configureMargin ()
        {
            if (button.isVisible())
            {
                borderInsets.right = button.getWidth() + rightGap;
            }
            else
            {
                borderInsets.right = 0;
            }
        }
    }

    public interface HintProvider
    {
        String getHint (JTextComponent source);
    }

    public interface FocusGainedHandler
    {
        void focusGained (JTextComponent source);
    }

}
