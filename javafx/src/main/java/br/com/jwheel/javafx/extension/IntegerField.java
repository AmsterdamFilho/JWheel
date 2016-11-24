package br.com.jwheel.javafx.extension;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class IntegerField extends FilteredTextField<Integer>
{
    private static final String                 numberRegex = "[0-9]+";
    private static final IntegerStringConverter converter   = new IntegerStringConverter();

    private int limit = Integer.MAX_VALUE;

    public void setLimit (int limit)
    {
        this.limit = limit;
    }

    @Override
    protected StringConverter<Integer> getConverter ()
    {
        return converter;
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newValue = change.getControlNewText();
        if (newValue.isEmpty())
        {
            return change;
        }
        else if (newValue.matches(numberRegex))
        {
            Long longNewValue = Long.valueOf(newValue);
            if (longNewValue > limit)
            {
                return null;
            }
            else
            {
                return change;
            }
        }
        else
        {
            return null;
        }
    }
}
