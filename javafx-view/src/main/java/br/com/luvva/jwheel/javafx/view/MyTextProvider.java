package br.com.luvva.jwheel.javafx.view;

import br.com.luvva.jwheel.provider.TextProvider;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MyTextProvider extends TextProvider
{
    //<editor-fold desc="Alerts">

    public static final String z_a_errorTitle = "z_a_errorTitle";

    public static final String z_a_successTitle = "z_a_successTitle";

    //</editor-fold>

    @Override
    protected String resourceBundlePath ()
    {
        return "jwheel-javafx-view/properties/text";
    }
}
