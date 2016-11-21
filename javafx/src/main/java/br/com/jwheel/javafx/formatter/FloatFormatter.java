package br.com.jwheel.javafx.formatter;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.text.DecimalFormatSymbols;

/**
 * A TextFormatter that ensures the content is either empty or a String that can be parsed to Float. The integer part of
 * the content is filtered so that it won't be higher than 99999.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FloatFormatter extends FilteredTextFormatter<Float>
{
    private static final String                 integerRegex;
    private static final String                 decimalSeparator;
    private static final StringConverter<Float> converter;

    static
    {
        integerRegex = "[0-9]{1,5}";
        converter = new StringConverter<Float>()
        {
            @Override
            public String toString (Float object)
            {
                if (object == null)
                {
                    return "";
                }
                return String.valueOf(object.floatValue()).replace(".", decimalSeparator);
            }

            @Override
            public Float fromString (String string)
            {
                if (string == null || string.isEmpty())
                {
                    return null;
                }
                return Float.valueOf(string.replace(decimalSeparator, "."));
            }
        };
        decimalSeparator = String.valueOf(DecimalFormatSymbols.getInstance().getDecimalSeparator());
    }

    private final float  limit;
    private final String floatRegex1;
    private final String floatRegex2;

    /**
     * Default constructor
     *
     * @param limit the maximum value accepted
     * @param scale the maximum decimal places accepted (up to five)
     */
    public FloatFormatter (float limit, int scale)
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
        floatRegex1 = "[0-9]{0,5}\\" + decimalSeparator + "[0-9]{1," + scale + "}";
        floatRegex2 = "[0-9]{1,5}\\" + decimalSeparator + "[0-9]{0," + scale + "}";
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
        else if (newValue.matches(floatRegex1) || newValue.matches(floatRegex2) || newValue.matches(integerRegex))
        {
            Float floatNewValue = Float.valueOf(newValue.replace(decimalSeparator, "."));
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
        else if (decimalSeparator.equals(newValue))
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

    @Override
    protected StringConverter<Float> getConverter ()
    {
        return converter;
    }
}
