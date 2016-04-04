package br.com.luvva.jwheel.java.utils;

import java.lang.reflect.ParameterizedType;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JavaLangUtils
{
    private JavaLangUtils ()
    {
    }

    public static <T> Class<T> getTypeArgumentClass (Class clazz)
    {
        return getTypeArgumentClass(clazz, 0);
    }

    public static <T> Class<T> getTypeArgumentClass (Class clazz, int index)
    {
        //noinspection unchecked
        return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index];
    }

}
