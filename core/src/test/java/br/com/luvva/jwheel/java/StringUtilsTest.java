package br.com.luvva.jwheel.java;

import org.junit.Assert;
import org.junit.Test;

import static br.com.luvva.jwheel.core.java.StringUtils.isNullOrEmpty;
import static br.com.luvva.jwheel.core.java.StringUtils.leftPadIntWithZeros;
import static br.com.luvva.jwheel.core.java.StringUtils.stripAccents;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class StringUtilsTest
{
    @Test
    public void testLeftPadIntWithZeros () throws Exception
    {
        Assert.assertTrue("01".equals(leftPadIntWithZeros(1, 2)));
        Assert.assertTrue("00".equals(leftPadIntWithZeros(0, 2)));
        Assert.assertTrue("00100".equals(leftPadIntWithZeros(100, 5)));
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    public void testStripAccents () throws Exception
    {
        Assert.assertTrue("".equals(stripAccents("")));
        Assert.assertTrue(" ".equals(stripAccents(" ")));
        Assert.assertTrue("".equals(stripAccents(null)));
        Assert.assertTrue("aeiouaoaeoc".equals(stripAccents("áéíóúãõâêôç")));
    }

    @Test
    public void testIsNullOrEmpty () throws Exception
    {
        Assert.assertTrue(isNullOrEmpty(null));
        Assert.assertTrue(isNullOrEmpty(""));
        Assert.assertTrue(isNullOrEmpty(" "));
        Assert.assertTrue(isNullOrEmpty("  "));
        Assert.assertTrue(isNullOrEmpty("          "));
        Assert.assertTrue(isNullOrEmpty("\t"));
        Assert.assertTrue(isNullOrEmpty("\t\t"));
        Assert.assertTrue(isNullOrEmpty("\n\n"));
        Assert.assertTrue(isNullOrEmpty("\n\n\t "));

        Assert.assertFalse(isNullOrEmpty("1"));
        Assert.assertFalse(isNullOrEmpty("Test"));
        Assert.assertFalse(isNullOrEmpty("Number"));
    }
}