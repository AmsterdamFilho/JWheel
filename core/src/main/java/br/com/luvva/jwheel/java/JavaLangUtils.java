package br.com.luvva.jwheel.java;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JavaLangUtils
{
    private JavaLangUtils ()
    {
    }

    /**
     * Gets the type argument of the generic super class of the parameter's class, according to the index parameter. It
     * does not work for generic interfaces!
     *
     * @param object the object which first superclass has a type argument
     * @param <T> the type argument class type
     * @return the type as a Class object
     */
    public static <T> Class<T> getSuperclassTypeArgument (Object object)
    {
        return getSuperclassTypeArgument(object, 0);
    }

    /**
     * Gets the type argument of the generic super class of the parameter's class, according to the index parameter. It
     * does not work for generic interfaces!
     *
     * @param object the object which first superclass has a type argument
     * @param index the desired type argument index
     * @param <T> the type argument class type
     * @return the type as a Class object
     */
    public static <T> Class<T> getSuperclassTypeArgument (Object object, int index)
    {
        Type genericSuperclass = object.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType)
        {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //noinspection unchecked
            return (Class<T>) parameterizedType.getActualTypeArguments()[index];
        }
        else
        {
            throw new IllegalArgumentException("Object's class does not have a ParameterizedType generic superclass!");
        }
    }
}
