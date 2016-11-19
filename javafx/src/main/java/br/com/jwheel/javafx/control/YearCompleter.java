package br.com.jwheel.javafx.control;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * A focus listener that completes a date in a TextField with just two digits for the year. When the focus is lost, if
 * the text matches [0-9]{2}/[0-9]{2}/[0-9]{2}, the last two digits are checked.
 * If they are equal or higher that the last two digits of LocalDate.now() + 5, the missing year digits are completed
 * with 19. They are completed with 20 otherwise. This behaviour is useful for dates of birth and also for dates nearby.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class YearCompleter
{
    private TextField textField;

    private final ChangeListener<Boolean> focusListener = (observable, oldValue, newValue) -> focusChanged(newValue);

    public void bind (TextField textField)
    {
        if (textField == null)
        {
            unbind();
        }
        else if (this.textField == null)
        {
            privateBind(textField);
        }
        else if (this.textField != textField)
        {
            unbind();
            privateBind(textField);
        }
    }

    public void unbind ()
    {
        if (textField != null)
        {
            textField.focusedProperty().removeListener(focusListener);
            textField = null;
        }
    }

    private void privateBind (TextField textField)
    {
        textField.focusedProperty().addListener(focusListener);
        this.textField = textField;
    }

    private void focusChanged (boolean isFocused)
    {
        if (!isFocused)
        {
            String text = textField.getText();
            if (text.matches("[0-9]{2}/[0-9]{2}/[0-9]{2}"))
            {
                String textYear19 = "19" + text.substring(6);
                String textYear20 = "20" + text.substring(6);
                if (Integer.valueOf(textYear20) >= LocalDate.now().getYear() + 5)
                {
                    textField.setText(text.substring(0, 6) + textYear19);
                }
                else
                {
                    textField.setText(text.substring(0, 6) + textYear20);
                }
            }
        }
    }
}
