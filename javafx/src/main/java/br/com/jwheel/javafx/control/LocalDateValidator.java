package br.com.jwheel.javafx.control;

import br.com.jwheel.core.model.converter.LocalDateMask;
import br.com.jwheel.javafx.view.MyResourceProvider;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import javax.inject.Inject;

import static br.com.jwheel.javafx.view.MyResourceProvider.invalidated;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalDateValidator
{
    private         TextField          textField;
    private         boolean            validated;
    private         Tooltip            backup;
    private @Inject LocalDateMask      converter;
    private @Inject MyResourceProvider resourceProvider;

    private final ChangeListener<Boolean> focusListener = (observable, oldValue, newValue) -> focusChanged(newValue);

    public boolean isValidated ()
    {
        if (textField == null)
        {
            throw new IllegalStateException("No control is set to be validated!");
        }
        return validated;
    }

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
            resetInvalidatedControl();
        }
    }

    private void privateBind (TextField textField)
    {
        textField.focusedProperty().addListener(focusListener);
        this.textField = textField;
        validate();
    }

    private void focusChanged (boolean isFocused)
    {
        if (isFocused && !validated)
        {
            resetInvalidatedControl();
        }
        else
        {
            validate();
            if (!validated)
            {
                configureInvalidatedControl();
            }
        }
    }

    private void configureInvalidatedControl ()
    {
        textField.getStylesheets().add(resourceProvider.getInvalidatedControlCss());
        backup = textField.getTooltip();
        textField.setTooltip(new Tooltip(resourceProvider.getI18nProperty(invalidated)));
    }

    private void resetInvalidatedControl ()
    {
        textField.getStylesheets().remove(resourceProvider.getInvalidatedControlCss());
        textField.setTooltip(backup);
    }

    private void validate ()
    {
        String text = textField.getText();
        validated = text.isEmpty() || converter.fromString(text) != null;
    }
}
