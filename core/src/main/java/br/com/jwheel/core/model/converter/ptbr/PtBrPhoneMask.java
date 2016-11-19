package br.com.jwheel.core.model.converter.ptbr;

import br.com.jwheel.core.cdi.Custom;
import br.com.jwheel.core.model.converter.PhoneMask;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Custom
public class PtBrPhoneMask extends PhoneMask
{
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
        int otherCharsCount = partial.replaceAll("[() -[0-9]+]+", "").length();
        return openParenthesisCount < 2 && closeParenthesisCount < 2 && dashCount < 2 && numbersCount < 12 &&
                spaceCount < 2 && otherCharsCount == 0;
    }

    @Override
    public String toString (String formattedPhone)
    {
        return formattedPhone == null ? "" : formattedPhone.replaceAll("[() -]+", "");
    }

    @Override
    public String fromString (String phoneNumbersOnly)
    {
        if (phoneNumbersOnly == null || phoneNumbersOnly.isEmpty())
        {
            return "";
        }
        else if (phoneNumbersOnly.matches("[0-9]{8}"))
        {
            return phoneNumbersOnly.substring(0, 4) + "-" + phoneNumbersOnly.substring(4);
        }
        else if (phoneNumbersOnly.matches("[0-9]{9}"))
        {
            return phoneNumbersOnly.substring(0, 5) + "-" + phoneNumbersOnly.substring(5);
        }
        else if (phoneNumbersOnly.matches("[0-9]{10}"))
        {
            return "(" + phoneNumbersOnly.substring(0, 2) + ") " + phoneNumbersOnly.substring(2, 6) + "-" +
                    phoneNumbersOnly.substring(6);
        }
        else if (phoneNumbersOnly.matches("[0-9]{11}"))
        {
            return "(" + phoneNumbersOnly.substring(0, 2) + ") " + phoneNumbersOnly.substring(2, 7) + "-" +
                    phoneNumbersOnly.substring(7);
        }
        else
        {
            return null;
        }
    }
}
