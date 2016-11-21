package br.com.jwheel.javafx.formatter;

import br.com.jwheel.core.model.mask.LocalPhoneMask;
import br.com.jwheel.core.model.mask.Mask;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class PhoneFormatter extends MaskedTextFormatter<String>
{
    private @Inject LocalPhoneMask localPhoneMask;

    @Override
    protected Mask<String> getMask ()
    {
        return localPhoneMask;
    }
}
