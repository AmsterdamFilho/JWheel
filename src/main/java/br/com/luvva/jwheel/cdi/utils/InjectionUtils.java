package br.com.luvva.jwheel.cdi.utils;

import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class InjectionUtils
{
    public static String getString (InjectionPoint injectionPoint)
    {
        for (Annotation annotation : injectionPoint.getQualifiers())
        {
            if (annotation.annotationType().equals(Initialized.class))
            {
                return ((Initialized) annotation).stringValue();
            }
        }
        return null;
    }
}
