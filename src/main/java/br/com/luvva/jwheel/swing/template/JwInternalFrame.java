package br.com.luvva.jwheel.swing.template;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public abstract class JwInternalFrame extends JInternalFrame implements InternalFrameListener
{

    @Override
    public void internalFrameOpened (InternalFrameEvent e)
    {

    }

    @Override
    public void internalFrameClosing (InternalFrameEvent e)
    {

    }

    @Override
    public void internalFrameClosed (InternalFrameEvent e)
    {

    }

    @Override
    public void internalFrameIconified (InternalFrameEvent e)
    {

    }

    @Override
    public void internalFrameDeiconified (InternalFrameEvent e)
    {

    }

    @Override
    public void internalFrameActivated (InternalFrameEvent e)
    {

    }

    @Override
    public void internalFrameDeactivated (InternalFrameEvent e)
    {

    }

    public void alignInDesktopPane ()
    {
        JDesktopPane jDesktopPane = getDesktopPane();
        if (jDesktopPane != null)
        {
            java.awt.Dimension jDesktopPaneSize = jDesktopPane.getSize();
            java.awt.Dimension size = getSize();
            setLocation((jDesktopPaneSize.width - size.width) / 2,
                    (jDesktopPaneSize.height - size.height) / 2);
        }
    }
}