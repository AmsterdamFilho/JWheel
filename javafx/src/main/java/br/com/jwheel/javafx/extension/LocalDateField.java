package br.com.jwheel.javafx.extension;

import br.com.jwheel.core.service.cdi.WeldContext;
import br.com.jwheel.core.service.formatter.Formatter;
import br.com.jwheel.core.service.formatter.LocalDateFormatter;

import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;
import java.time.LocalDate;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalDateField extends FormattedTextField<LocalDate>
{
    @Override
    protected Formatter<LocalDate> getFormatter ()
    {
        return WeldContext.getInstance().getBean(LocalDateFormatter.class, new AnnotationLiteral<Default>() {});
    }
}
