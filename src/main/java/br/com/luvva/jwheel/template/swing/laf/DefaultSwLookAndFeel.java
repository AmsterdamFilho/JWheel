package br.com.luvva.jwheel.template.swing.laf;

import javax.enterprise.context.ApplicationScoped;
import javax.swing.border.Border;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@ApplicationScoped
public class DefaultSwLookAndFeel implements SwLookAndFeel
{

    private Border defaultBorder = new BlackAndWhiteEtchedBorder();

    @Override
    public Border getDefaultBorder ()
    {
        return defaultBorder;
    }

}
