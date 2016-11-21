package br.com.jwheel.javafx.formatter;

import br.com.jwheel.core.model.mask.Mask;
import br.com.jwheel.javafx.view.MyResourceProvider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextFormatter;

import javax.inject.Inject;

/**
 * A FilteredTextFormatter based on a {@link Mask}. It filters the input, but it is not guaranteed that the text will be
 * valid at any time. When focus is lost, the input will be formatted, the control will be validated, and the control
 * will be styled if validation fails.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class MaskedTextFormatter<T> extends FilteredTextFormatter<T>
{
    private MaskValidator maskValidator = new MaskValidator();
    private MaskFormatter maskFormatter = new MaskFormatter();

    private @Inject MyResourceProvider resourceProvider;

    public boolean isValidated ()
    {
        return maskValidator.isValidated();
    }

    @Override
    protected void unbindImpl ()
    {
        super.unbindImpl();
        getNode().focusedProperty().removeListener(maskFormatter);
        getNode().focusedProperty().removeListener(maskValidator);
        if (!maskValidator.isValidated())
        {
            maskValidator.resetInvalidatedControl();
        }
    }

    @Override
    protected void bindImpl ()
    {
        super.bindImpl();
        // the order is important! formatting must occur first than validation
        getNode().focusedProperty().addListener(maskFormatter);
        getNode().focusedProperty().addListener(maskValidator);
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

        public boolean isValidated ()
        {
            if (getNode() == null)
            {
                throw new IllegalStateException("No control is set to be validated!");
            }
            return validated;
        }

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
            getNode().getStylesheets().add(resourceProvider.getInvalidatedControlCss());
        }

        private void resetInvalidatedControl ()
        {
            getNode().getStylesheets().remove(resourceProvider.getInvalidatedControlCss());
        }

        private void validate ()
        {
            String text = getNode().getText();
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
                String text = getNode().getText();
                String formatted = getMask().formatComplete(text);
                if (formatted != null)
                {
                    getNode().setText(formatted);
                }
            }
        }
    }

    protected abstract Mask<T> getMask ();
}
