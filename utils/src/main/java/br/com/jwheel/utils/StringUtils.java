package br.com.jwheel.utils;

import java.text.Normalizer;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class StringUtils
{
    public static String stripAccents (String string)
    {
        if (string == null)
        {
            return "";
        }
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static boolean isNullOrEmpty (String string)
    {
        return string == null || string.trim().isEmpty();
    }

    /**
     * Left pads an integer with zeros and return a String
     *
     * @param intToPad the number to be transformed in a fixed length String
     * @param fixedLength the length of the returned String
     * @return the padded integer as a fixed length String
     */
    public static String leftPadIntWithZeros (int intToPad, int fixedLength)
    {
        return String.format("%0" + fixedLength + "d", intToPad);
    }
}
