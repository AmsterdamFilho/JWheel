package br.com.luvva.jwheel.view.swing.components;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwButton extends JButton
{
    public JwButton (String text, ActionListener al)
    {
        super(text);
        addActionListener(al);
    }
}
