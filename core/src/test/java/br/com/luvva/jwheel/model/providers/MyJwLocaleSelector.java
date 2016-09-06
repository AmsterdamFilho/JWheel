package br.com.luvva.jwheel.model.providers;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MyJwLocaleSelector implements JwLocaleSelector
{
    @Override
    public JwLocale selectLocale ()
    {
        return JwLocale.EN_US;
    }
}
