package br.com.jwheel.javafx.formatter;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

/**
 * A TextFormatter that does not allow that the content length go beyond a specified limit.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LengthFormatter extends FilteredTextFormatter<String>
{
    private static final StringConverter<String> converter = new DefaultStringConverter();

    private int limit;

    public LengthFormatter (int limit)
    {
        this.limit = limit;
    }

    @Override
    protected StringConverter<String> getConverter ()
    {
        return converter;
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newValue = change.getControlNewText();
        if (newValue.length() > limit)
        {
            return null;
        }
        else
        {
            return change;
        }
    }
}
