package br.com.jwheel.core.service.formatter;

import br.com.jwheel.core.service.cdi.WeldContext;
import br.com.jwheel.core.service.java.ProviderDelegate;
import br.com.jwheel.core.service.formatter.ptbr.PtBrLocalPhoneFormatter;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LocalPhoneFormatterFactory
{
    private static final ProviderDelegate<LocalPhoneFormatter> PROVIDER_DELEGATE;

    static
    {
        // some code can be added to achieve automatic i18n
        PROVIDER_DELEGATE = () -> WeldContext.getInstance().getBean(PtBrLocalPhoneFormatter.class);
    }

    @Produces
    public LocalPhoneFormatter produce ()
    {
        return PROVIDER_DELEGATE.provide();
    }
}
