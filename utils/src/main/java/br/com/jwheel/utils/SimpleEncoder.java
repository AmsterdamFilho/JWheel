package br.com.jwheel.utils;

import java.util.Base64;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleEncoder
{
    public static String encode (String string)
    {
        if (string == null)
        {
            return "";
        }
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    public static String decode (String string)
    {
        if (string == null)
        {
            return "";
        }
        return new String(Base64.getDecoder().decode(string));
    }
}
