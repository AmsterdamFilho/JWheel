package br.com.jwheel.core.model.mask;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.ProviderDelegate;
import br.com.jwheel.core.model.mask.ptbr.PtBrLocalPhoneMask;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LocalPhoneMaskFactory
{
    private static final ProviderDelegate<LocalPhoneMask> PROVIDER_DELEGATE;

    static
    {
        // some code can be added to achieve automatic i18n
        PROVIDER_DELEGATE = () -> WeldContext.getInstance().getBean(PtBrLocalPhoneMask.class);
    }

    @Produces
    public LocalPhoneMask produce ()
    {
        return PROVIDER_DELEGATE.provide();
    }
}
