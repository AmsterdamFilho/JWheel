package br.com.jwheel.core.model.mask;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.ProviderDelegate;
import br.com.jwheel.core.model.mask.ptbr.PtBrLocalDateMask;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LocalDateMaskFactory
{
    private static final ProviderDelegate<LocalDateMask> PROVIDER_DELEGATE;

    static
    {
        // some code can be added to achieve automatic i18n
        PROVIDER_DELEGATE = () -> WeldContext.getInstance().getBean(PtBrLocalDateMask.class);
    }

    @Produces
    public LocalDateMask produce ()
    {
        return PROVIDER_DELEGATE.provide();
    }
}
