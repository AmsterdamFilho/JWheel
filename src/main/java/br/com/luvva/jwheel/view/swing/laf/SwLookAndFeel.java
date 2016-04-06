package br.com.luvva.jwheel.view.swing.laf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface SwLookAndFeel
{
    Border defaultBorder ();

    LookAndFeel getLookAndFeel ();

    void highlight (JTextComponent jTextComponent);
}
