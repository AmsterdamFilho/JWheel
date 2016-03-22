package br.com.luvva.jwheel.model.utils;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class Wrapper<T>
{
    private T value;

    public T getValue ()
    {
        return value;
    }

    public void setValue (T value)
    {
        this.value = value;
    }
}
