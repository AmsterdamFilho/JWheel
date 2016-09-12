package br.com.luvva.jwheel.java;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JavaLangUtilsTest
{
    @Test
    public void testGetSuperclassTypeArgument () throws Exception
    {
        Assert.assertEquals(Integer.class, JavaLangUtils.getSuperclassTypeArgument(new Implementation()));
    }

    @Test
    public void testGetSuperclassTypeArgumentWithIndex () throws Exception
    {
        Assert.assertEquals(String.class, JavaLangUtils.getSuperclassTypeArgument(new Implementation(), 1));
    }

    private class Implementation extends AbstractGenericClass<Integer, String>
    {

    }

    private abstract class AbstractGenericClass<T, Y>
    {

    }
}