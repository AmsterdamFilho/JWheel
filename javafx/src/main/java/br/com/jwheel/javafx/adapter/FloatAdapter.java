package br.com.jwheel.javafx.adapter;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.text.DecimalFormatSymbols;

/**
 * An Adapter that ensures the content is either empty or a String that can be parsed to Float and is not greater than
 * a specified limit.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FloatAdapter extends AdapterByFilter<Float>
{
    private static final String                 integerRegex;
    private static final String                 decimalSeparator;
    private static final StringConverter<Float> converter;

    static
    {
        integerRegex = "[0-9]+";
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
     * @param limit the maximum value accepted
     * @param scale the maximum decimal places accepted
     */
    public FloatAdapter (float limit, int scale)
    {
        if (scale <= 0)
        {
            throw new IllegalArgumentException("Scale should be greater than 0!");
        }
        if (limit < 0)
        {
            throw new IllegalArgumentException("Limit can not be negative!");
        }
        this.limit = limit;
        floatRegex1 = "[0-9]*\\" + decimalSeparator + "[0-9]{1," + scale + "}";
        floatRegex2 = "[0-9]+\\" + decimalSeparator + "[0-9]{0," + scale + "}";
    }

    @Override
    protected StringConverter<Float> getConverter ()
    {
        return converter;
    }

    @Override
    public TextFormatter.Change apply (TextFormatter.Change change)
    {
        String newValue = change.getControlNewText();
        if (newValue.isEmpty())
        {
            return change;
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
        // if new value might be parsed to a Float, check if it is not greater than the limit
        else if (newValue.matches(floatRegex1) || newValue.matches(floatRegex2) || newValue.matches(integerRegex))
        {
            Float floatNewValue;
            try
            {
                floatNewValue = Float.valueOf(newValue.replace(decimalSeparator, "."));
            }
            catch (NumberFormatException e)
            {
                return null;
            }
            if (floatNewValue > limit)
            {
                return null;
            }
            else
            {
                return change;
            }
        }
        else
        {
            return null;
        }
    }
}
