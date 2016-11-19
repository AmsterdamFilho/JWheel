package br.com.jwheel.javafx.control;

import br.com.jwheel.core.model.converter.PhoneMask;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class PhoneFilter extends MaskFilter
{
    @Inject
    public PhoneFilter (PhoneMask mask)
    {
        super(mask);
    }
}
