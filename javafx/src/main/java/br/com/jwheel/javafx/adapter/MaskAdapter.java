package br.com.jwheel.javafx.adapter;

import br.com.jwheel.core.model.mask.Mask;
import br.com.jwheel.javafx.MyResourceProvider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class MaskAdapter<T> extends FilterAdapter<T>
{
    private final MaskValidator maskValidator = new MaskValidator();
    private final MaskFormatter maskFormatter = new MaskFormatter();

    private @Inject MyResourceProvider resourceProvider;

    public boolean isValidated ()
    {
        return maskValidator.isValidated();
    }

    @Override
    protected void resetImpl ()
    {
        super.resetImpl();
        getControl().focusedProperty().removeListener(maskFormatter);
        getControl().focusedProperty().removeListener(maskValidator);
        if (!maskValidator.isValidated())
        {
            maskValidator.resetInvalidatedControl();
        }
    }

    @Override
    protected void adaptImpl ()
    {
        super.adaptImpl();
        // the order is important! formatting must occur first than validation
        getControl().focusedProperty().addListener(maskFormatter);
        getControl().focusedProperty().addListener(maskValidator);
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

        private boolean isValidated ()
        {
            if (getControl() == null)
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
            getControl().getStylesheets().add(resourceProvider.getInvalidatedControlCss());
            getControl().textProperty().addListener(textListener);
        }

        private void resetInvalidatedControl ()
        {
            getControl().getStylesheets().remove(resourceProvider.getInvalidatedControlCss());
            getControl().textProperty().removeListener(textListener);
        }

        private void validate ()
        {
            String text = getControl().getText();
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
                String text = getControl().getText();
                String formatted = getMask().formatComplete(text);
                if (formatted != null)
                {
                    getControl().setText(formatted);
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
