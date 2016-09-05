package br.com.luvva.jwheel.model.providers;

import br.com.luvva.jwheel.WeldContext;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class TextProviderFactory
{
    private @Inject LanguageSelector languageSelector;

    @Produces
    private TextProvider produceTextProvider ()
    {
        LANGUAGE language = languageSelector.selectLanguage();
        if (language == null)
        {
            return getDefault();
        }
        switch (language)
        {
            case EN_US:
                return WeldContext.getInstance().getBean(TextProviderEnUs.class);
            case PT_BR:
                return WeldContext.getInstance().getBean(TextProviderPtBr.class);
            default:
                return getDefault();

        }
    }

    private TextProvider getDefault ()
    {
        return WeldContext.getInstance().getBean(TextProviderEnUs.class);
    }
}
