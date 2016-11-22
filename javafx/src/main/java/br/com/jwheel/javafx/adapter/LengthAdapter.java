package br.com.jwheel.javafx.adapter;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LengthAdapter extends AdapterByFilter<String>
{
    private static final StringConverter<String> converter = new DefaultStringConverter();

    private int limit;

    public LengthAdapter (int limit)
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
