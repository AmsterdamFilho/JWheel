package br.com.luvva.jwheel.javafx.control;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * A TextFormatter filter that ensures the content can be parsed to an Integer or is empty. When set, the TextControl
 * initial content must be valid.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class IntegerFilter implements UnaryOperator<TextFormatter.Change>
{
    private final int limit;

    private static final String numberRegex = "[0-9]+";

    public IntegerFilter ()
    {
        this(Integer.MAX_VALUE);
    }

    public IntegerFilter (int limit)
    {
        if (limit < 0)
        {
            throw new IllegalArgumentException("Limit can not be negative!");
        }
        this.limit = limit;
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
