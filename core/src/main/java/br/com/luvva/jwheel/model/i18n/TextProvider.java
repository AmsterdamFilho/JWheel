package br.com.luvva.jwheel.model.i18n;

import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface TextProvider
{

    //<editor-fold desc="Alerts">

    String z_a_errorTitle = "z_a_errorTitle";
    
    String z_a_internalErrorMessage = "z_a_internalErrorMessage";

    String z_a_saveFailedMessage = "z_a_saveFailedMessage";

    String z_a_saveSucceededMessage = "z_a_saveSucceededMessage";

    String z_a_successTitle = "z_a_successTitle";

    //</editor-fold>

    //<editor-fold desc="Connection settings">

    String z_cs_ddmConfigureConnectionOption = "z_cs_ddmConfigureConnectionOption";

    String z_cs_ddmExitOption = "z_cs_ddmExitOption";

    String z_cs_ddmTitle = "z_cs_ddmTitle";

    String z_cs_ddmTryAgainOption = "z_cs_ddmTryAgainOption";
    
    String z_cs_testFailedMessage = "z_cs_testFailedMessage";

    String z_cs_testSucceededMessage = "z_cs_testSucceededMessage";

    String z_cs_title = "z_cs_title";

    //</editor-fold>

    String getText (String property);

    ResourceBundle getResourceBundle ();
}
