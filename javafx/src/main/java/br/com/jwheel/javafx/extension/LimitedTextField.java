package br.com.jwheel.javafx.extension;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LimitedTextField extends TextField implements UnaryOperator<TextFormatter.Change>
{
    private int limit = 255;

    public LimitedTextField ()
    {
        setTextFormatter(new TextFormatter<>(this));
    }

    public void setLimit (int limit)
    {
        this.limit = limit;
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
