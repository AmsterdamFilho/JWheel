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
    public Border defaultBorder ()
    {
        return defaultBorder;
    }

    @Override
    public int verticalSpaceBetweenLabelAndComponent ()
    {
        return 3;
    }

    @Override
    public int leftInsetsBetweenComponents ()
    {
        return 5;
    }

    @Override
    public int topInsetsBetweenComponents ()
    {
        return 5;
    }
}
