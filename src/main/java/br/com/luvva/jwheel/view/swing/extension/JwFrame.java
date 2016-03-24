package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.view.swing.builders.JwFrameBuilder;
import br.com.luvva.jwheel.view.swing.laf.SwLookAndFeel;
import br.com.luvva.jwheel.view.swing.utils.SwingUtils;
import br.com.luvva.jwheel.model.providers.TextProvider;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwFrame extends JFrame
{
    private JwDesktopPane jwDesktopPane = WeldContext.getInstance().getBean(JwDesktopPane.class);

    private @Inject JwFrameBuilder jwFrameBuilder;
    private @Inject SwLookAndFeel  jwLookAndFeel;
    private @Inject TextProvider   textProvider;
    private @Inject Logger         logger;
    private @Inject SwingUtils     swingUtils;

    public static final String MAIN_CARD = "main_card";

    public JwFrame () throws HeadlessException
    {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing (WindowEvent e)
            {
                JwFrame.this.windowClosing();
            }
        });
        configureJFrame();
    }

    protected void configureJFrame ()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @PostConstruct
    protected void initComponents ()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JMenuBar mb = jwFrameBuilder.getJMenuBar();
        if (mb != null)
        {
            mb.setBorder(jwLookAndFeel.defaultBorder());
            setJMenuBar(mb);
        }
        JComponent pageStartPanel = jwFrameBuilder.getPageStartPanel();
        if (pageStartPanel != null)
        {
            pageStartPanel.setBorder(jwLookAndFeel.defaultBorder());
            mainPanel.add(pageStartPanel, BorderLayout.PAGE_START);
        }
        jwFrameBuilder.getDesktopPaneComponents().forEach(
                cp -> jwDesktopPane.add(cp, javax.swing.JLayeredPane.DEFAULT_LAYER));
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBorder(jwLookAndFeel.defaultBorder());
        jScrollPane.add(jwDesktopPane);
        jScrollPane.setViewportView(jwDesktopPane);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);
        JComponent pageEndPanel = jwFrameBuilder.getPageEndPanel();
        if (pageEndPanel != null)
        {
            pageEndPanel.setBorder(jwLookAndFeel.defaultBorder());
            mainPanel.add(pageEndPanel, BorderLayout.PAGE_END);
        }
        CardLayoutPanel contentPane = new CardLayoutPanel();
        contentPane.addCard(mainPanel, MAIN_CARD);
        contentPane.setSelectedCard(MAIN_CARD);
        setContentPane(contentPane);
    }

    protected void selectInternalFrame (JInternalFrame internalFrame)
    {
        try
        {
            if (internalFrame.isIcon())
            {
                internalFrame.setIcon(false);
            }
            if (!internalFrame.isVisible())
            {
                internalFrame.setVisible(true);
            }
            internalFrame.setSelected(true);
        }
        catch (PropertyVetoException ex)
        {
            logger.debug("", ex);
        }
        internalFrame.moveToFront();
        if (internalFrame instanceof JwInternalFrame)
        {
            ((JwInternalFrame) internalFrame).alignInDesktopPane();
        }
        jwDesktopPane.resizeDesktop();
    }

    protected void windowClosing ()
    {
        boolean userConfirmed = swingUtils.getUserConfirmation(textProvider.exitSystemQuestion(), this);

        if (userConfirmed && validateExit())
        {
            for (JInternalFrame jInternalFrame : jwDesktopPane.getAllFrames())
            {
                if (jInternalFrame instanceof JwInternalFrame)
                {
                    JwInternalFrame intFrame = (JwInternalFrame) jInternalFrame;
                    intFrame.moveToFront();
                    if (!intFrame.validateExit())
                    {
                        return;
                    }
                }
            }
            dispose();
        }
    }

    protected boolean validateExit ()
    {
        return true;
    }

}
