package br.com.jwheel.javafx.extension;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.mask.LocalDateMask;
import br.com.jwheel.core.model.mask.Mask;

import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;
import java.time.LocalDate;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalDateField extends MaskedTextField<LocalDate>
{
    @Override
    protected Mask<LocalDate> getMask ()
    {
        return WeldContext.getInstance().getBean(LocalDateMask.class, new AnnotationLiteral<Default>() {});
    }
}
