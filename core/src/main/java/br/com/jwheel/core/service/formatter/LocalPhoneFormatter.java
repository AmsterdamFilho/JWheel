package br.com.jwheel.core.service.formatter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class LocalPhoneFormatter extends Formatter<String>
{
    /**
     * Converts a phone number object to a String representation of it.
     *
     * @param phoneNumber a phone number representation (it has only digits, since everything else is formatting)
     * @return a phone number formatted as String, with hyphen, parenthesis and everything else
     */
    public abstract String toString (String phoneNumber);

    /**
     * Converts a phone number object to a String representation of it.
     *
     * @param formattedPhoneNumber a phone number formatted as String, with hyphen, parenthesis and everything else
     * @return a phone number representation (it has only digits, since everything else is formatting)
     */
    public abstract String fromString (String formattedPhoneNumber);
}
