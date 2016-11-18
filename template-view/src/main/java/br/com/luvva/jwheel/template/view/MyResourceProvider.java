package br.com.luvva.jwheel.template.view;

import br.com.luvva.jwheel.provider.ResourceProvider;

import javax.inject.Singleton;
import java.io.InputStream;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class MyResourceProvider extends ResourceProvider
{
    @Override
    protected String rootResourceDirectory ()
    {
        return "jwheel-template-view/laf/default";
    }

    public InputStream csdFxml ()
    {
        return getResourceInputStream(fxmlPath() + "csd" + fxmlExtension());
    }

    public String recordPaneCss ()
    {
        return getResourceURL(cssPath() + "record-pane" + cssExtension());
    }

}
