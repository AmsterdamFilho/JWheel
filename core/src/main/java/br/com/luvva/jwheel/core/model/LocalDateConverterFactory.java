package br.com.luvva.jwheel.core.model;

import br.com.luvva.jwheel.core.cdi.WeldContext;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LocalDateConverterFactory
{
    private static final ProviderDelegate<LocalDateConverter> PROVIDER_DELEGATE;

    static
    {
        // some code can be added to achieve automatic i18n
        PROVIDER_DELEGATE = () -> WeldContext.getInstance().getBean(PtBrLocalDateConverter.class);
    }

    @Produces
    public LocalDateConverter produce ()
    {
        return PROVIDER_DELEGATE.provide();
    }
}
