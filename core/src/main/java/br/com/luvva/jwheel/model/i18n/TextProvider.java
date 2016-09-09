package br.com.luvva.jwheel.model.i18n;

import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface TextProvider
{

    //<editor-fold desc="General">

    String g_error = "g-error";
    
    String g_internalError = "g-internalError";

    String g_saveFailed = "g-saveFailed";

    String g_saveSucceeded = "g-saveSucceeded";

    String g_success = "g-success";

    //</editor-fold>

    //<editor-fold desc="Connection settings">
    
    String cs_testFailed = "cs-testFailed";

    String cs_testSucceeded = "cs-testSucceeded";

    String cs_title = "cs-title";

    String cs_ddmConfigureConnection = "cs-ddmConfigureConnection";

    String cs_ddmExit = "cs-ddmExit";

    String cs_ddmTitle = "cs-ddmTitle";

    String cs_ddmTryAgain = "cs-ddmTryAgain";

    //</editor-fold>

    String getText (String property);

    ResourceBundle getResourceBundle ();
}
