package br.com.jwheel.javafx.view;

import br.com.jwheel.core.model.ResourceProvider;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class MyResourceProvider extends ResourceProvider
{
    @Override
    protected String root ()
    {
        return "jwheel-javafx-view";
    }

    public String getInvalidatedControlCss ()
    {
        return getCss("invalidated");
    }
}
