package br.com.luvva.jwheel.template.provider;

import br.com.luvva.jwheel.provider.TextProvider;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MyTextProvider extends TextProvider
{

    //<editor-fold desc="Alerts">

    public static final String z_a_internalErrorMessage = "z_a_internalErrorMessage";

    public static final String z_a_saveFailedMessage = "z_a_saveFailedMessage";

    public static final String z_a_saveSucceededMessage = "z_a_saveSucceededMessage";

    //</editor-fold>

    //<editor-fold desc="Connection settings">

    public static final String z_cs_ddmConfigureConnectionOption = "z_cs_ddmConfigureConnectionOption";

    public static final String z_cs_ddmExitOption = "z_cs_ddmExitOption";

    public static final String z_cs_ddmTitle = "z_cs_ddmTitle";

    public static final String z_cs_ddmTryAgainOption = "z_cs_ddmTryAgainOption";

    public static final String z_cs_testFailedMessage = "z_cs_testFailedMessage";

    public static final String z_cs_testSucceededMessage = "z_cs_testSucceededMessage";

    public static final String z_cs_title = "z_cs_title";

    //</editor-fold>

    @Override
    protected String resourceBundlePath ()
    {
        return "jwheel-template/properties/text";
    }
}
