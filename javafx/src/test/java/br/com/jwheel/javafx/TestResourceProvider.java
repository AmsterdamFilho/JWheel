package br.com.jwheel.javafx;

import br.com.jwheel.utils.ResourceProvider;

import java.net.URL;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class TestResourceProvider extends ResourceProvider implements JavaFxResourceProvider
{
    public URL getExtensionDemo1Fxml ()
    {
        return getFxml("extension-demo1");
    }

    public URL getExtensionDemo2Fxml ()
    {
        return getFxml("extension-demo2");
    }

    @Override
    public String getInvalidatedControlCss ()
    {
        return getCss("invalidated");
    }

    @Override
    protected String root ()
    {
        return "jwheel-javafx";
    }
}
