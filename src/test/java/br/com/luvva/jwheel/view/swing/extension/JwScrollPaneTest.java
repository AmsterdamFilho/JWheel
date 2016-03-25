package br.com.luvva.jwheel.view.swing.extension;

import org.junit.Test;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwScrollPaneTest
{

    @Test
    public void testUpdateScrollBars () throws Exception
    {
        EventQueue.invokeLater(this::showTestFrame);
//        Thread.sleep(1000000000);
    }

    private void showTestFrame ()
    {
        JwScrollPane jwScrollPane = new JwScrollPane();
        jwScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jwScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        FriendlyView textFieldsPanel = new FriendlyView(jwScrollPane);
        jwScrollPane.setViewportView(textFieldsPanel);

        BoldLabel infoLabel = new BoldLabel("Type an index from 0 to 99 and press enter to select a Text Field");
        JTextField indexTextField = new JTextField();
        indexTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased (KeyEvent e)
            {
                if (e.getKeyChar() == '\n')
                {
                    String text = indexTextField.getText();
                    if (text.matches("[0-9]{1,2}"))
                    {
                        textFieldsPanel.requestFocus(Integer.valueOf(text));
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid index!");
                    }
                }
            }
        });
        indexTextField.setColumns(2);
        JPanel pageEndPanel = new JPanel();
        pageEndPanel.add(infoLabel);
        pageEndPanel.add(indexTextField);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(jwScrollPane, BorderLayout.CENTER);
        contentPane.add(pageEndPanel, BorderLayout.PAGE_START);

        JFrame jFrame = new JFrame();
        jFrame.setTitle("JwScrollPaneTest");
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setContentPane(contentPane);
        jFrame.setPreferredSize(new Dimension(600, 350));
        jFrame.setMinimumSize(new Dimension(600, 350));
        jFrame.setMaximumSize(new Dimension(600, 350));
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private class TextFieldForTheTest extends JwTextField
    {
        private JwScrollPane jwScrollPane;
        private FriendlyView friendlyView;
        private int index;

        private TextFieldForTheTest (JwScrollPane jwScrollPane, FriendlyView friendlyView, int index)
        {
            this.jwScrollPane = jwScrollPane;
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained (FocusEvent e)
                {
                    friendlyView.setSelectedIndex(index);
                    jwScrollPane.updateScrollBars();
                }

                @Override
                public void focusLost (FocusEvent e)
                {
                    friendlyView.setSelectedIndex(-1);
                }
            });
            this.index = index;
            setMinimumSize(new Dimension(100, 60));
            setMaximumSize(new Dimension(100, 60));
            setPreferredSize(new Dimension(100, 60));
        }
    }

    private class FriendlyView extends JwScrollPane.FriendlyView
    {

        private List<JTextComponent> jTextComponents = new ArrayList<>();
        private int selectedIndex = -1;

        private FriendlyView (JwScrollPane jwScrollPane)
        {
            setLayout(new GridLayout(10, 10));
            for (int i = 0; i < 100; i++)
            {
                TextFieldForTheTest txt = new TextFieldForTheTest(jwScrollPane, this, i);
                add(txt);
                jTextComponents.add(txt);
            }
        }

        private void requestFocus (int textFieldIndex)
        {
            jTextComponents.get(textFieldIndex).requestFocusInWindow();
        }

        private void setSelectedIndex (int selectedIndex)
        {
            this.selectedIndex = selectedIndex;
        }

        @Override
        public boolean shouldUpdateVertically ()
        {
            return true;
        }

        @Override
        public boolean shouldUpdateHorizontally ()
        {
            return true;
        }

        @Override
        public int getVerticalUnitsCount ()
        {
            return 10;
        }

        @Override
        public int getSelectedVerticalUnitIndex ()
        {
            if (selectedIndex == -1)
            {
                return -1;
            }
            return selectedIndex / 10;
        }

        @Override
        public int getHorizontalUnitsCount ()
        {
            return 10;
        }

        @Override
        public int getSelectedHorizontalUnitIndex ()
        {
            return selectedIndex % 10;
        }
    }

}