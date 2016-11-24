package br.com.jwheel.javafx.extension;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.mask.LocalPhoneMask;
import br.com.jwheel.core.model.mask.Mask;

import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalPhoneField extends MaskedTextField<String>
{
    @Override
    protected Mask<String> getMask ()
    {
        return WeldContext.getInstance().getBean(LocalPhoneMask.class, new AnnotationLiteral<Default>() {});
    }
}
