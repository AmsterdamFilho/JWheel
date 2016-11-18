package br.com.luvva.jwheel.javafx.provider;

import br.com.luvva.jwheel.provider.TextProvider;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MyTextProvider extends TextProvider
{
    @Override
    protected String resourceBundlePath ()
    {
        return "jwheel-javafx/properties/text";
    }
}
