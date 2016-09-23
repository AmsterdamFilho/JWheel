package br.com.luvva.jwheel.template.i18n;

import br.com.luvva.jwheel.cdi.Custom;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Custom
public class DefaultTextProvider implements TextProvider
{
    private @Inject Logger logger;

    private ResourceBundle resourceBundle;

    @Override
    public String getText (String property)
    {
        try
        {
            return resourceBundle.getString(property);
        }
        catch (Exception e)
        {
            logger.error("Could not get resource for property: " + (property == null ? "" : property), e);
            return "<" + (property == null ? "" : property) + ">";
        }
    }

    @Override
    public ResourceBundle getResourceBundle ()
    {
        return resourceBundle;
    }

    void setResourceBundle (ResourceBundle resourceBundle)
    {
        this.resourceBundle = resourceBundle;
    }
}
