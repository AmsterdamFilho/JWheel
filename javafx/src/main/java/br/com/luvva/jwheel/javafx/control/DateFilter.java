package br.com.luvva.jwheel.javafx.control;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * A TextFormatter filter that helps a user to insert a Date in a Text Control. When set, the TextControl initial
 * content must be valid. The text in the control might be empty, a valid date or an incomplete date.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DateFilter implements UnaryOperator<TextFormatter.Change>
{
    public DateFilter ()
    {
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newText = change.getControlNewText();
        if (newText.isEmpty())
        {
            return change;
        }
        // if new text is a 3 digit number, add the missing date separator
        else if (newText.matches("[0-9]{3}"))
        {
            change.setRange(0, change.getControlText().length());
            String formattedNewText = newText.substring(0, 2) + "/" + newText.substring(2);
            change.setText(formattedNewText);
            change.setCaretPosition(formattedNewText.length());
            change.setAnchor(formattedNewText.length());
            return change;
        }
        // if new text is 2 digits followed by the date separator and another 2 digits, add the missing date separator
        else if (newText.matches("[0-9]{2}/[0-9]{3}"))
        {
            change.setRange(0, change.getControlText().length());
            String formattedNewText = newText.substring(0, 5) + "/" + newText.substring(5);
            change.setText(formattedNewText);
            change.setCaretPosition(formattedNewText.length());
            change.setAnchor(formattedNewText.length());
            return change;
        }
        // if new text does not have more than 2 date separators and does not have more than 8 digits
        // and does not have other characters than date separators or digits, allow the change
        else if (newText.replaceAll("[^/]", "").length() < 3 && newText.replaceAll("[^0-9]", "").length() < 9 &&
                    newText.replaceAll("[0-9/]", "").length() == 0)
        {
            return change;
        }
        else
        {
            return null;
        }
    }
}
