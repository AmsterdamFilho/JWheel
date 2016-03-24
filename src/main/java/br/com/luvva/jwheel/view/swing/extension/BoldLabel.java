package br.com.luvva.jwheel.view.swing.extension;

import javax.swing.*;
import java.awt.Font;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class BoldLabel extends JLabel
{
    public BoldLabel ()
    {
        this("");
    }

    public BoldLabel (String text)
    {
        this(text, SwingConstants.LEFT);
    }

    public BoldLabel (String text, int alignment)
    {
        super(text);
        setFont(getFont().deriveFont(Font.BOLD));
        setHorizontalAlignment(alignment);
    }
}
