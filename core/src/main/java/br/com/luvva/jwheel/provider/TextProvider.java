package br.com.luvva.jwheel.provider;

import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class TextProvider
{
    private @Inject Logger         logger;
    private final   ResourceBundle resourceBundle;

    public TextProvider ()
    {
        resourceBundle = ResourceBundle.getBundle(resourceBundlePath());
    }

    public ResourceBundle getResourceBundle ()
    {
        return resourceBundle;
    }

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

    protected abstract String resourceBundlePath ();
}
