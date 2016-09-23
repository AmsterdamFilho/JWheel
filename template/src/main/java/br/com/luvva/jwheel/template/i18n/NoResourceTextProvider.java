package br.com.luvva.jwheel.template.i18n;

import br.com.luvva.jwheel.cdi.Custom;

import javax.inject.Singleton;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Custom
public class NoResourceTextProvider implements TextProvider
{
    @Override
    public String getText (String property)
    {
        return "Error...";
    }

    @Override
    public ResourceBundle getResourceBundle ()
    {
        return null;
    }
}
