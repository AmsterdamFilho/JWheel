package br.com.jwheel.javafx.extension;

import br.com.jwheel.core.service.cdi.WeldContext;
import br.com.jwheel.core.service.formatter.Formatter;
import br.com.jwheel.core.service.formatter.LocalPhoneFormatter;

import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalPhoneField extends FormattedTextField<String>
{
    @Override
    protected Formatter<String> getFormatter ()
    {
        return WeldContext.getInstance().getBean(LocalPhoneFormatter.class, new AnnotationLiteral<Default>() {});
    }
}
