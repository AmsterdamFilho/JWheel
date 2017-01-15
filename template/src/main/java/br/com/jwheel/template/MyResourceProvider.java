package br.com.jwheel.template;

import br.com.jwheel.core.service.java.ResourceProvider;

import javax.inject.Singleton;
import java.io.InputStream;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class MyResourceProvider extends ResourceProvider
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

    @Override
    protected String root ()
    {
        return "jwheel-template";
    }

    public InputStream csdFxml ()
    {
        return getFxml("csd");
    }

    public String recordPaneCss ()
    {
        return getCss("record-pane");
    }
}
