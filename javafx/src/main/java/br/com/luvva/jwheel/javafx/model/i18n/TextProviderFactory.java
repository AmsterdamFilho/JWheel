package br.com.luvva.jwheel.javafx.model.i18n;

import br.com.luvva.jwheel.cdi.WeldContext;
import org.slf4j.Logger;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class TextProviderFactory
{
    private         TextProvider textProvider;
    private @Inject Logger       logger;

    @Produces
    public TextProvider getTextProvider ()
    {
        if (textProvider == null)
        {
            try
            {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/jw-locale");
                DefaultTextProvider defaultTextProvider = WeldContext.getInstance().getBean(DefaultTextProvider.class);
                defaultTextProvider.setResourceBundle(resourceBundle);
                textProvider = defaultTextProvider;
            }
            catch (Exception e)
            {
                logger.error("Could not load JWheel's locale resource!", e);
                textProvider = WeldContext.getInstance().getBean(NoResourceTextProvider.class);
            }
        }
        return textProvider;
    }
}
