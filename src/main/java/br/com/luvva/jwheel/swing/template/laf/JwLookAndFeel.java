package br.com.luvva.jwheel.swing.template.laf;

import javax.swing.*;
import javax.swing.border.Border;

public interface JwLookAndFeel
{
    Border getDefaultBorder ();

    JSeparator createHorizontalJSeparator ();

    JSeparator createVerticalJSeparator ();
}
