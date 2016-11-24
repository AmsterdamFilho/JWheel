package br.com.jwheel.javafx.extension;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.mask.Mask;
import br.com.jwheel.javafx.MyResourceProvider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class MaskedTextField<T> extends FilteredTextField<T>
{
    private final MaskValidator maskValidator = new MaskValidator();

    public MaskedTextField ()
    {
        // the order is important! formatting must occur first than validation
        focusedProperty().addListener(new MaskFormatter());
        focusedProperty().addListener(maskValidator);
    }

    public boolean isValidated ()
    {
        return maskValidator.validated;
    }

    private String getInvalidatedControlCss ()
    {
        return WeldContext.getInstance().getBean(MyResourceProvider.class).getInvalidatedControlCss();
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newText = change.getControlNewText();
        if (getMask().validatePartial(newText))
        {
            String formattedNewText = getMask().formatPartial(newText);
            if (formattedNewText != null)
            {
                change.setRange(0, change.getControlText().length());
                change.setText(formattedNewText);
                change.setCaretPosition(formattedNewText.length());
                change.setAnchor(formattedNewText.length());
            }
            return change;
        }
        else
        {
            return null;
        }
    }

    private class MaskValidator implements ChangeListener<Boolean>
    {
        private boolean validated;
        private final ChangeListener<String> textListener = (observable, oldValue, newValue) -> textChanged();

        @Override
        public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
        {
            if (newValue)
            {
                if (!validated)
                {
                    // if got the focus and control is in error state
                    resetInvalidatedControl();
                }
            }
            else
            {
                validate();
                if (!validated)
                {
                    // if lost the focus and control is in error state
                    configureInvalidatedControl();
                }
            }
        }

        private void configureInvalidatedControl ()
        {
            getStylesheets().add(getInvalidatedControlCss());
            textProperty().addListener(textListener);
        }

        private void resetInvalidatedControl ()
        {
            getStylesheets().remove(getInvalidatedControlCss());
            textProperty().removeListener(textListener);
        }

        private void validate ()
        {
            String text = getText();
            if (text.isEmpty())
            {
                validated = true;
            }
            else
            {
                T t = getMask().fromString(text);
                validated = !(t == null || "".equals(t));
            }
        }

        private void textChanged ()
        {
            validate();
            if (validated)
            {
                resetInvalidatedControl();
            }
        }
    }

    private class MaskFormatter implements ChangeListener<Boolean>
    {
        @Override
        public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
        {
            // if focus is lost
            if (!newValue)
            {
                // format the text
                String text = getText();
                String formatted = getMask().formatComplete(text);
                if (formatted != null)
                {
                    setText(formatted);
                }
            }
        }
    }

    @Override
    protected StringConverter<T> getConverter ()
    {
        return getMask();
    }

    protected abstract Mask<T> getMask ();
}
