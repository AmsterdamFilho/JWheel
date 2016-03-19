package br.com.luvva.jwheel.view.swing.components;

import javax.swing.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class JwInternalFrame extends JInternalFrame
{

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

    public boolean validateExit ()
    {
        return true;
    }
}