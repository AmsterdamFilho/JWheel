package br.com.luvva.jwheel.swing.template.laf;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
public interface SwLookAndFeel
{
    Border getDefaultBorder ();

    JSeparator createHorizontalJSeparator ();

    JSeparator createVerticalJSeparator ();
}
