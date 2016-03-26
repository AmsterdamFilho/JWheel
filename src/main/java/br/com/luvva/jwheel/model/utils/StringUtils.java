package br.com.luvva.jwheel.model.utils;

import java.text.Normalizer;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class StringUtils
{
    public static String stripAccents (String string)
    {
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
