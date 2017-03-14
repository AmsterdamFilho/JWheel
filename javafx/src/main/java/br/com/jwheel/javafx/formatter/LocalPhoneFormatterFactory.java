package br.com.jwheel.javafx.formatter;

import br.com.jwheel.cdi.WeldContext;
import br.com.jwheel.utils.ProviderDelegate;
import br.com.jwheel.javafx.formatter.ptbr.PtBrLocalPhoneFormatter;

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
        PROVIDER_DELEGATE = () -> WeldContext.getInstance().getAny(PtBrLocalPhoneFormatter.class);
    }

    @Produces
    public LocalPhoneFormatter produce ()
    {
        return PROVIDER_DELEGATE.provide();
    }
}
