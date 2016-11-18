package br.com.jwheel.javafx.control;

import br.com.jwheel.core.model.LocalDateConverter;
import javafx.scene.control.TextFormatter;

import javax.inject.Inject;
import java.util.function.UnaryOperator;

/**
 * A TextFormatter filter that helps a user to insert a Date in a Text Control. When set, the TextControl initial
 * content must be valid. It filters the input, but it is not guaranteed that the text can represent a Date at any time.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalDateFilter implements UnaryOperator<TextFormatter.Change>
{
    private @Inject LocalDateConverter converter;

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newText = change.getControlNewText();
        if (converter.validatePartial(newText))
        {
            String formattedNewText = converter.formatPartial(newText);
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
}
