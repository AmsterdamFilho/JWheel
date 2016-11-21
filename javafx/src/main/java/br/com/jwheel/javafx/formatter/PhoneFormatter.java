package br.com.jwheel.javafx.formatter;

import br.com.jwheel.core.model.mask.LocalPhoneMask;
import br.com.jwheel.core.model.mask.Mask;
import javafx.util.StringConverter;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class PhoneFormatter extends MaskedTextFormatter<String>
{
    private @Inject LocalPhoneMask localPhoneMask;

    @Override
    protected StringConverter<String> getConverter ()
    {
        return localPhoneMask;
    }

    @Override
    protected Mask<String> getMask ()
    {
        return localPhoneMask;
    }
}
