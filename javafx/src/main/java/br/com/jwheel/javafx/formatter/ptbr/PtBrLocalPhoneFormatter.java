package br.com.jwheel.javafx.formatter.ptbr;

import br.com.jwheel.cdi.Custom;
import br.com.jwheel.javafx.formatter.LocalPhoneFormatter;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Custom
public class PtBrLocalPhoneFormatter extends LocalPhoneFormatter
{
    private static final String validPhone1 = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}";
    private static final String validPhone2 = "[0-9]{4,5}-[0-9]{4}";

    @Override
    public String formatPartial (String partial)
    {
        return null;
    }

    @Override
    public boolean validateNotEmptyPartial (String partial)
    {
        int openParenthesisCount = partial.replaceAll("[^(]", "").length();
        int closeParenthesisCount = partial.replaceAll("[^)]", "").length();
        int dashCount = partial.replaceAll("[^\\-]", "").length();
        int numbersCount = partial.replaceAll("[^0-9]", "").length();
        int spaceCount = partial.replaceAll("[^ ]", "").length();
        int otherCharsCount = partial.replaceAll("[() -[0-9]]+", "").length();
        return openParenthesisCount < 2 && closeParenthesisCount < 2 && dashCount < 2 && numbersCount < 12 &&
                spaceCount < 2 && otherCharsCount == 0;
    }

    @Override
    public String formatComplete (String complete)
    {
        if (complete == null || complete.isEmpty() || complete.matches(validPhone1) || complete.matches(validPhone2))
        {
            return null;
        }
        String numbersOnlyString = complete.replaceAll("[^0-9]", "");
        if (numbersOnlyString.isEmpty())
        {
            return null;
        }
        else
        {
            int numbersOnlyLength = numbersOnlyString.length();
            if (numbersOnlyLength > 7 && numbersOnlyLength < 12)
            {
                return toString(numbersOnlyString);
            }
            else
            {
                return null;
            }
        }
    }

    @Override
    public String toString (String phoneNumber)
    {
        if (phoneNumber == null)
        {
            return "";
        }
        else if (phoneNumber.matches("[0-9]{8}"))
        {
            return phoneNumber.substring(0, 4) + "-" + phoneNumber.substring(4);
        }
        else if (phoneNumber.matches("[0-9]{9}"))
        {
            return phoneNumber.substring(0, 5) + "-" + phoneNumber.substring(5);
        }
        else if (phoneNumber.matches("[0-9]{10}"))
        {
            return "(" + phoneNumber.substring(0, 2) + ") " + phoneNumber.substring(2, 6) + "-" +
                    phoneNumber.substring(6);
        }
        else if (phoneNumber.matches("[0-9]{11}"))
        {
            return "(" + phoneNumber.substring(0, 2) + ") " + phoneNumber.substring(2, 7) + "-" +
                    phoneNumber.substring(7);
        }
        else
        {
            return "";
        }
    }

    @Override
    public String fromString (String formattedPhoneNumber)
    {
        if (formattedPhoneNumber == null)
        {
            return "";
        }
        else if (formattedPhoneNumber.matches(validPhone1) || formattedPhoneNumber.matches(validPhone2))
        {
            return formattedPhoneNumber.replaceAll("[^0-9]", "");
        }
        else
        {
            return "";
        }
    }
}
