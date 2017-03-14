package br.com.jwheel.javafx.formatter;

import br.com.jwheel.cdi.WeldContext;
import br.com.jwheel.utils.ProviderDelegate;
import br.com.jwheel.javafx.formatter.ptbr.PtBrLocalDateFormatter;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LocalDateFormatterFactory
{
    private static final ProviderDelegate<LocalDateFormatter> PROVIDER_DELEGATE;

    static
    {
        // some code can be added to achieve automatic i18n
        PROVIDER_DELEGATE = () -> WeldContext.getInstance().getAny(PtBrLocalDateFormatter.class);
    }

    @Produces
    public LocalDateFormatter produce ()
    {
        return PROVIDER_DELEGATE.provide();
    }
}
