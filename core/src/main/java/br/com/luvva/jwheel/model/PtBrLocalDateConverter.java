package br.com.luvva.jwheel.model;

import br.com.luvva.jwheel.cdi.Custom;

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
}
