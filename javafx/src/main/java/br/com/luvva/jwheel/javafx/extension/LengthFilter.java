package br.com.luvva.jwheel.javafx.extension;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * A TextFormatter filter that does not allow that the content length go beyond a specified limit. When set, the initial
 * content length must be valid.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LengthFilter implements UnaryOperator<TextFormatter.Change>
{
    private int maximumLength;

    public LengthFilter (int maximumLength)
    {
        if (maximumLength < 0)
        {
            throw new IllegalArgumentException("Maximum length must not be negative!");
        }
        this.maximumLength = maximumLength;
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newValue = change.getControlNewText();
        if (newValue.length() > maximumLength)
        {
            return null;
        }
        else
        {
            return change;
        }
    }
}
