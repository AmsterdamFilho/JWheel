package br.com.luvva.jwheel.javafx.extension;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.text.DecimalFormatSymbols;

/**
 * A class that decorates a text field in a such a way that will only display either an empty String or a text
 * that can be parsed to a Double within specified bound. Negative values will not be accepted. When decorated,
 * the text field content will be set to an empty String.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class NumberTfDecorator
{
    private final double    limit;
    private final TextField textField;
    private final String    decimalNumberRegex;

    private final ChangeListener<String> myChangeListener = this::changed;

    private static final char decimalSeparator = DecimalFormatSymbols.getInstance().getDecimalSeparator();

    /**
     * Constructs a decorator that accepts all values from zero to Integer.MAX_VALUE and has no decimal places
     *
     * @param textField the textField to be decorated
     */
    public NumberTfDecorator (TextField textField)
    {
        this(textField, Integer.MAX_VALUE);
    }

    /**
     * Constructs a decorator that accepts all values from zero to limit and has no decimal places
     *
     * @param textField the textField to be decorate
     * @param limit     the maximum accepted value
     */
    public NumberTfDecorator (TextField textField, double limit)
    {
        this(textField, limit, 0);
    }

    /**
     * Constructs a decorator that accepts all values from 0 to limit and has
     *
     * @param textField the textField to be decorated
     * @param limit     the maximum accepted value
     * @param scale     the maximum decimal places accepted
     */
    public NumberTfDecorator (TextField textField, double limit, int scale)
    {
        this.limit = limit;
        this.textField = textField;
        if (limit < 0)
        {
            throw new IllegalArgumentException("Limit can not be negative!");
        }
        if (scale < 0)
        {
            throw new IllegalArgumentException("Scale can not be negative!");
        }
        textField.setText("");
        textField.textProperty().addListener(myChangeListener);
        if (scale > 0)
        {
            decimalNumberRegex = "[0-9]*" + decimalSeparator + "?[0-9]{0," + scale + "}";
        }
        else
        {
            decimalNumberRegex = "[0-9]+";
        }
    }

    /**
     * Undo the textField decoration
     */
    public void undo ()
    {
        textField.textProperty().removeListener(myChangeListener);
    }

    private void changed (ObservableValue<? extends String> observable, String oldValue, String newValue)
    {
        String validatedNewValue = validateNewValue(oldValue, newValue);
        if (validatedNewValue != null)
        {
            ((StringProperty) observable).setValue(validatedNewValue);
        }
    }

    /**
     * Checks if the new value is acceptable. If it is, returns null. Returns a value to be set otherwise
     *
     * @param oldValue the old value of the text property
     * @param newValue the new value trying to be set
     * @return the new value to be set, or null is requested new value is ok
     */
    private String validateNewValue (String oldValue, String newValue)
    {
        if (newValue == null)
        {
            return oldValue;
        }
        else if ("".equals(newValue))
        {
            return null;
        }
        else if (String.valueOf(decimalSeparator).equals(newValue))
        {
            return "0" + String.valueOf(decimalSeparator);
        }
        else if (newValue.matches(decimalNumberRegex))
        {
            Double doubleNewValue = Double.valueOf(newValue.replaceAll(",", "."));
            if (doubleNewValue > limit)
            {
                return oldValue;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return oldValue;
        }
    }
}
