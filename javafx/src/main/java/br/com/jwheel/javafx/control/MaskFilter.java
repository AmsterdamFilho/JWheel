package br.com.jwheel.javafx.control;

import br.com.jwheel.core.model.converter.MaskFormatter;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * A TextFormatter filter that helps a user to insert a masked text in a Text Control. When set, the TextControl initial
 * content must be valid. It filters the input, but it is not guaranteed that the text is valid at any time.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MaskFilter implements UnaryOperator<TextFormatter.Change>
{
    private MaskFormatter maskFormatter;

    public MaskFilter (MaskFormatter maskFormatter)
    {
        this.maskFormatter = maskFormatter;
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newText = change.getControlNewText();
        if (maskFormatter.validatePartial(newText))
        {
            String formattedNewText = maskFormatter.formatPartial(newText);
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
