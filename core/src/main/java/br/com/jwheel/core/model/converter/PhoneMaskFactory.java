package br.com.jwheel.core.model.converter;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.ProviderDelegate;
import br.com.jwheel.core.model.converter.ptbr.PtBrPhoneMask;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class PhoneMaskFactory
{
    private static final ProviderDelegate<PhoneMask> PROVIDER_DELEGATE;

    static
    {
        // some code can be added to achieve automatic i18n
        PROVIDER_DELEGATE = () -> WeldContext.getInstance().getBean(PtBrPhoneMask.class);
    }

    @Produces
    public PhoneMask produce ()
    {
        return PROVIDER_DELEGATE.provide();
    }
}
