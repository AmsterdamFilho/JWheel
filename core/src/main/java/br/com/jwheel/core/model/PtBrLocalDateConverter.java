package br.com.jwheel.core.model;

import br.com.jwheel.core.cdi.Custom;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Custom
public class PtBrLocalDateConverter extends LocalDateConverter
{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    @Override
    protected boolean validateNotEmptyPartial (String partial)
    {
        return partial.replaceAll("[^/]", "").length() < 3 && partial.replaceAll("[^0-9]", "").length() < 9 &&
                partial.replaceAll("[0-9/]", "").length() == 0;
    }

    @Override
    public String formatPartial (String partial)
    {
        // if partial is a 3 digit number, add the missing date separator
        if (partial.matches("[0-9]{3}"))
        {
            return partial.substring(0, 2) + "/" + partial.substring(2);
        }
        // if partial has 2 digits followed by the date separator and 3 more digits, add the missing date separator
        else if (partial.matches("[0-9]{2}/[0-9]{3}"))
        {
            return partial.substring(0, 5) + "/" + partial.substring(5);
        }
        return null;
    }
}
