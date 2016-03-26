package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.view.swing.utils.SwingUtils;

import javax.swing.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwPasswordField extends JPasswordField
{
    public JwPasswordField ()
    {
        SwingUtils.addEnterAsForwardTraversalKey(this);
    }

    public String getStringPassword ()
    {
        return new String(getPassword());
    }
}
