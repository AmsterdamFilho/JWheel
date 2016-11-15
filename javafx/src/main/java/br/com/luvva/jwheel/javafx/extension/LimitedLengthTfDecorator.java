package br.com.luvva.jwheel.javafx.extension;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LimitedLengthTfDecorator
{
    private final int       maxLength;
    private final TextField textField;

    private final ChangeListener<String> myChangeListener = this::changed;

    /**
     * Constructs a decorator that with a maximum text length
     *
     * @param textField the textField to be decorated
     */
    public LimitedLengthTfDecorator (TextField textField, int maxLength)
    {
        if (maxLength < 1)
        {
            throw new IllegalArgumentException("Maximum length must higher than zero!");
        }
        this.maxLength = maxLength;
        this.textField = textField;
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
        if (newValue == null || newValue.length() > maxLength)
        {
            ((StringProperty) observable).setValue(oldValue);
        }
    }
}
