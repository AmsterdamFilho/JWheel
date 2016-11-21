package br.com.jwheel.javafx.formatter;

import br.com.jwheel.core.model.mask.LocalDateMask;
import br.com.jwheel.core.model.mask.Mask;
import javafx.util.StringConverter;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalDateFormatter extends MaskedTextFormatter<LocalDate>
{
    private @Inject LocalDateMask localDateMask;

    @Override
    protected StringConverter<LocalDate> getConverter ()
    {
        return localDateMask;
    }

    @Override
    protected Mask<LocalDate> getMask ()
    {
        return localDateMask;
    }
}
