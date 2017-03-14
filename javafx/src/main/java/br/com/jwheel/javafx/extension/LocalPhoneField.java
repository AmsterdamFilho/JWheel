package br.com.jwheel.javafx.extension;

import br.com.jwheel.cdi.WeldContext;
import br.com.jwheel.javafx.formatter.Formatter;
import br.com.jwheel.javafx.formatter.LocalPhoneFormatter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalPhoneField extends FormattedTextField<String>
{
    @Override
    protected Formatter<String> getFormatter ()
    {
        return WeldContext.getInstance().getDefault(LocalPhoneFormatter.class);
    }
}
