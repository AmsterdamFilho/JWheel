package br.com.jwheel.javafx;

import br.com.jwheel.core.service.java.ResourceProvider;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class JwJavaFxResourceProvider extends ResourceProvider
{
    @Override
    protected String root ()
    {
        return "jwheel-javafx";
    }

    public String getInvalidatedControlCss ()
    {
        return getCss("invalidated");
    }

    public String getAutoCompleteListCss ()
    {
        return getCss("auto-complete-list");
    }

    public String getAutoCompleteTableCss ()
    {
        return getCss("auto-complete-table");
    }
}
