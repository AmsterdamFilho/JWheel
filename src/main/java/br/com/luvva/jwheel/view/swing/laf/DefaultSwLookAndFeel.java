package br.com.luvva.jwheel.view.swing.laf;

import javax.inject.Singleton;
import javax.swing.border.Border;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class DefaultSwLookAndFeel implements SwLookAndFeel
{

    private Border defaultBorder = new BlackAndWhiteEtchedBorder();

    @Override
    public Border getDefaultBorder ()
    {
        return defaultBorder;
    }

}
