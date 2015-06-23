package br.com.luvva.jwheel.swing.template.crud;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.swing.components.CardLayoutPanel;
import br.com.luvva.jwheel.swing.template.laf.JwLookAndFeel;
import br.com.luvva.jwheel.swing.utils.SwingUtils;
import br.com.luvva.jwheel.text.TextProvider;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;

public class JwFrame extends JFrame
{
    private JwDesktopPane jwDesktopPane = WeldContext.getInstance().getBean(JwDesktopPane.class);

    @Inject
    private JwFrameBuilder jwFrameBuilder;

    @Inject
    private JwLookAndFeel jwLookAndFeel;

    @Inject
    private TextProvider textProvider;

    @Inject
    private Logger logger;

    @Inject
    private SwingUtils swingUtils;

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
            mb.setBorder(jwLookAndFeel.getDefaultBorder());
            setJMenuBar(mb);
        }
        JPanel toolBar = jwFrameBuilder.getPageStartPanel();
        if (toolBar != null)
        {
            toolBar.setBorder(jwLookAndFeel.getDefaultBorder());
            mainPanel.add(toolBar, BorderLayout.PAGE_START);
        }
        Component[] cps = jwFrameBuilder.getDesktopPaneComponents();
        if (cps != null)
        {
            for (Component cp : cps)
            {
                jwDesktopPane.add(cp, javax.swing.JLayeredPane.DEFAULT_LAYER);
            }
        }
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBorder(jwLookAndFeel.getDefaultBorder());
        jScrollPane.add(jwDesktopPane);
        jScrollPane.setViewportView(jwDesktopPane);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);
        JPanel pnlStatus = jwFrameBuilder.getPageEndPanel();
        if (pnlStatus != null)
        {
            pnlStatus.setBorder(jwLookAndFeel.getDefaultBorder());
            mainPanel.add(pnlStatus, BorderLayout.PAGE_END);
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
        boolean userConfirmed = swingUtils.getUserConfirmation(textProvider.getExitSystemQuestion(), this);

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
