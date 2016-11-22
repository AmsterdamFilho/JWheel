package br.com.jwheel.javafx.adapter;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LengthAdapter extends FilterAdapter<String>
{
    private static final StringConverter<String> converter = new MyConverter();

    private final int limit;

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

    private static class MyConverter extends StringConverter<String>
    {
        @Override
        public String toString (String object)
        {
            return object == null ? "" : object;
        }

        @Override
        public String fromString (String string)
        {
            return string == null ? "" : string;
        }
    }
}
