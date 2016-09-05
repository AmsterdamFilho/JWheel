package br.com.luvva.jwheel.model.providers;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MyLanguageSelector implements LanguageSelector
{
    @Override
    public LANGUAGE selectLanguage ()
    {
        return LANGUAGE.EN_US;
    }
}
