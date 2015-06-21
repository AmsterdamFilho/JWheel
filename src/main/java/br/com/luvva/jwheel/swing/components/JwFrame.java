package br.com.luvva.jwheel.swing.components;

import br.com.luvva.jwheel.swing.utils.SwingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import static br.com.luvva.jwheel.JWheelResourcesFactory.textProvider;

public abstract class JwFrame extends JFrame implements WindowListener
{
    private JwDesktopPane jwDesktopPane;
    private CardsPnl cardsPnl = new CardsPnl();
    private JPanel   pnlMdi   = new JPanel();

    public static final String CARD_MDI = "card_mdi";
    private final static Logger logger = LoggerFactory.getLogger(JwFrame.class);

    public JwFrame (String title) throws HeadlessException
    {
        super(title);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new CardLayout());
        addWindowListener(this);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(cardsPnl);
    }

    public abstract boolean validateExit ();

    public void selectInternalFrame (JInternalFrame internalFrame)
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

    @Override
    public void windowOpened (WindowEvent e)
    {

    }

    @Override
    public void windowClosing (WindowEvent e)
    {
        boolean userConfirmed = SwingUtils.getUserConfirmation(textProvider.getExitSystemQuestion(), this);

        if (userConfirmed && validateExit())
        {
            for (JInternalFrame jInternalFrame : jwDesktopPane.getAllFrames())
            {
                if (jInternalFrame instanceof JwInternalFrame)
                {
                    JwInternalFrame intFrame = (JwInternalFrame) jInternalFrame;
                    intFrame.moveToFront();
                    intFrame.internalFrameClosing(null);
                    if (!intFrame.isClosed())
                    {
                        return;
                    }
                }
            }
            dispose();
        }
    }

    @Override
    public void windowClosed (WindowEvent e)
    {

    }

    @Override
    public void windowIconified (WindowEvent e)
    {

    }

    @Override
    public void windowDeiconified (WindowEvent e)
    {

    }

    @Override
    public void windowActivated (WindowEvent e)
    {

    }

    @Override
    public void windowDeactivated (WindowEvent e)
    {

    }

}
