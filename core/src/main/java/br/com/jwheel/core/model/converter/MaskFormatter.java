package br.com.jwheel.core.model.converter;

import javafx.util.StringConverter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class MaskFormatter<T> extends StringConverter<T>
{
    /**
     * Checks if the String argument is accepted as a partial String being typed
     *
     * @param partial the partial String
     */
    public boolean validatePartial (String partial)
    {
        return partial.isEmpty() || validateNotEmptyPartial(partial);
    }

    /**
     * Formats a partial String being typed
     *
     * @param partial the partial String
     * @return the formatted String or null if it was not possible or necessary to format
     */
    public abstract String formatPartial (String partial);

    public abstract boolean validateNotEmptyPartial (String partial);
}
