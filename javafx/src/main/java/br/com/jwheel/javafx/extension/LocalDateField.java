package br.com.jwheel.javafx.extension;

import br.com.jwheel.cdi.WeldContext;
import br.com.jwheel.javafx.formatter.Formatter;
import br.com.jwheel.javafx.formatter.LocalDateFormatter;

import java.time.LocalDate;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalDateField extends FormattedTextField<LocalDate>
{
    @Override
    protected Formatter<LocalDate> getFormatter ()
    {
        return WeldContext.getInstance().getDefault(LocalDateFormatter.class);
    }
}
