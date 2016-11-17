package br.com.luvva.jwheel.model;

import javafx.util.StringConverter;

import java.time.LocalDate;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class LocalDateConverter extends StringConverter<LocalDate>
{
    /**
     * Checks if the String argument is accepted as a partial String being typed to be a LocalDate
     *
     * @param partial the partial String
     */
    public boolean validatePartial (String partial)
    {
        return partial.isEmpty() || validateNotEmptyPartial(partial);
    }

    /**
     * Formats a partial String being typed to be a LocalDate
     *
     * @param partial the partial String
     * @return the formatted String or null if it was not possible or necessary to format
     */
    public abstract String formatPartial (String partial);

    protected abstract boolean validateNotEmptyPartial (String partial);
}
