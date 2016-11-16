package br.com.luvva.jwheel.javafx.extension;

import javafx.scene.control.TextFormatter;

import java.text.DecimalFormatSymbols;
import java.util.function.UnaryOperator;

/**
 * A TextFormatter filter that ensures the content is either empty or a String that can be parsed to Float. When set,
 * the TextControl initial content must be valid. The integer part of the content is filtered so that it won't be higher
 * than 99999.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DecimalFilter implements UnaryOperator<TextFormatter.Change>
{
    private final float  limit;
    private final String decimalRegex1;
    private final String decimalRegex2;

    private static final String integerRegex     = "[0-9]{1,5}";
    private static final char   decimalSeparator = DecimalFormatSymbols.getInstance().getDecimalSeparator();

    /**
     * Default constructor
     *
     * @param limit the maximum value accepted
     * @param scale the maximum decimal places accepted (up to five)
     */
    public DecimalFilter (float limit, int scale)
    {
        if (scale <= 0 || scale > 5)
        {
            throw new IllegalArgumentException("Scale should be between 1 and 5!");
        }
        if (limit < 0)
        {
            throw new IllegalArgumentException("Limit can not be negative!");
        }
        this.limit = limit;
        decimalRegex1 = "[0-9]{0,5}\\" + decimalSeparator + "[0-9]{1," + scale + "}";
        decimalRegex2 = "[0-9]{1,5}\\" + decimalSeparator + "[0-9]{0," + scale + "}";
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newValue = change.getControlNewText();
        if (newValue.isEmpty())
        {
            return change;
        }
        // if new value can be parsed to a Float, check if it is not greater than the limit
        else if (newValue.matches(decimalRegex1) || newValue.matches(decimalRegex2) || newValue.matches(integerRegex))
        {
            Float floatNewValue = Float.valueOf(newValue.replaceAll(",", "."));
            if (floatNewValue > limit)
            {
                return null;
            }
            else
            {
                return change;
            }
        }
        // if new value is equal to a decimal separator, add a zero before it so it can be parsed to a Float
        else if (String.valueOf(decimalSeparator).equals(newValue))
        {
            change.setRange(0, change.getControlText().length());
            change.setText("0" + decimalSeparator);
            change.setCaretPosition(2);
            change.setAnchor(2);
            return change;
        }
        else
        {
            return null;
        }
    }
}
