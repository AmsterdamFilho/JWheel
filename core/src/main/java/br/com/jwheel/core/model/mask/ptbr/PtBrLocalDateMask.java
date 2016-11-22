package br.com.jwheel.core.model.mask.ptbr;

import br.com.jwheel.core.cdi.Custom;
import br.com.jwheel.core.model.mask.LocalDateMask;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Custom
public class PtBrLocalDateMask extends LocalDateMask
{
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String toString (LocalDate localDate)
    {
        return localDate == null ? "" : formatter.format(localDate);
    }

    @Override
    public LocalDate fromString (String localDateString)
    {
        try
        {
            return LocalDate.parse(localDateString, formatter);
        }
        catch (DateTimeParseException e)
        {
            return null;
        }
    }

    /**
     * Validates if partial do not contain more than two date separator, more than 8 digits, or any other character
     *
     * @param partial the partial String to be validated
     * @return true if partial is valid
     */
    @Override
    public boolean validateNotEmptyPartial (String partial)
    {
        return partial.replaceAll("[^/]", "").length() < 3 && partial.replaceAll("[^0-9]", "").length() < 9 &&
                partial.replaceAll("[0-9/]", "").length() == 0;
    }

    /**
     * If the date String has only two digits for the year, complete them according to this rule: If they are equal or
     * higher that the last two digits of LocalDate.now() + 5, the missing year digits are completed with 19. They are
     * completed with 20 otherwise. This behaviour is useful for dates of birth and also for dates nearby.
     * <p>
     * If the date String has exactly 8 digits, they are formatted to a date, whatever are the other characters.
     *
     * @param complete the complete String
     * @return the formatted LocalDate, or null if it was not possible or necessary to format the String to a LocalDate
     */
    @Override
    public String formatComplete (String complete)
    {
        if (complete == null || complete.isEmpty() || fromString(complete) != null)
        {
            return null;
        }
        else if (complete.matches("[0-9]{2}/[0-9]{2}/[0-9]{2}"))
        {
            String textYear19 = "19" + complete.substring(6);
            String textYear20 = "20" + complete.substring(6);
            if (Integer.valueOf(textYear20) >= LocalDate.now().getYear() + 5)
            {
                return complete.substring(0, 6) + textYear19;
            }
            else
            {
                return complete.substring(0, 6) + textYear20;
            }
        }
        else if (complete.replaceAll("[^0-9]", "").length() == 8)
        {
            String onlyDigits = complete.replaceAll("[^0-9]", "");
            return onlyDigits.substring(0, 2) + "/" + onlyDigits.substring(2, 4) + "/" + onlyDigits.substring(4);
        }
        else
        {
            return null;
        }
    }

    /**
     * If partial is a 3 digit number, add the missing date separator. If it has 2 digits followed by the date separator
     * and 3 more digits, add the missing date separator
     *
     * @param partial the partial String
     * @return the formatted partial String or null if it doesn't match the expected pattern
     */
    @Override
    public String formatPartial (String partial)
    {
        if (partial.matches("[0-9]{3}"))
        {
            return partial.substring(0, 2) + "/" + partial.substring(2);
        }
        else if (partial.matches("[0-9]{2}/[0-9]{3}"))
        {
            return partial.substring(0, 5) + "/" + partial.substring(5);
        }
        return null;
    }
}
