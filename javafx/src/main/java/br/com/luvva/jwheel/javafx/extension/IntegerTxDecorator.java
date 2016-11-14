package br.com.luvva.jwheel.javafx.extension;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * A class that decorates a text field in a such a way that will only display either an empty String or a text
 * that can be parsed to an Integer within specified bound. Negative values will not be accepted. When decorated,
 * the text field content will be set to an empty String.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class IntegerTxDecorator
{
    private final int       upperBound;
    private final TextField textField;

    private final ChangeListener<String> myChangeListener = this::changed;

    /**
     * Constructs a decorator that accepts all values from zero to Integer.MAX_VALUE
     *
     * @param textField the textField to be decorated
     */
    public IntegerTxDecorator (TextField textField)
    {
        this(textField, Integer.MAX_VALUE);
    }

    /**
     * Constructs a decorator that accepts all values from 0 to upperBound
     *
     * @param textField  the textField to be decorated
     * @param upperBound the upper bound
     */
    public IntegerTxDecorator (TextField textField, int upperBound)
    {
        this.upperBound = upperBound;
        this.textField = textField;
        if (upperBound < 0)
        {
            throw new IllegalArgumentException("Upper bound can not be negative!");
        }
        textField.setText("");
        textField.textProperty().addListener(myChangeListener);
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
        if (!valueIsAccepted(newValue))
        {
            ((StringProperty) observable).setValue(oldValue);
        }
    }

    private boolean valueIsAccepted (String newValue)
    {
        if (newValue == null)
        {
            return false;
        }
        else if ("".equals(newValue))
        {
            return true;
        }
        else if (!newValue.matches("[0-9]{1,10}"))
        {
            return false;
        }
        else
        {
            Long aLong = Long.valueOf(newValue);
            return aLong <= upperBound;
        }
    }
}
