package br.com.luvva.jwheel.model;

import java.util.Base64;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleEncoder
{
    public static String encode (String string)
    {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    public static String decode (String string)
    {
        return new String(Base64.getDecoder().decode(string));
    }
}
