package br.com.jwheel.core.service.java;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class StringUtilsTest
{
    @Test
    public void testLeftPadIntWithZeros () throws Exception
    {
        Assert.assertTrue("01".equals(StringUtils.leftPadIntWithZeros(1, 2)));
        Assert.assertTrue("00".equals(StringUtils.leftPadIntWithZeros(0, 2)));
        Assert.assertTrue("00100".equals(StringUtils.leftPadIntWithZeros(100, 5)));
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    public void testStripAccents () throws Exception
    {
        Assert.assertTrue("".equals(StringUtils.stripAccents("")));
        Assert.assertTrue(" ".equals(StringUtils.stripAccents(" ")));
        Assert.assertTrue("".equals(StringUtils.stripAccents(null)));
        Assert.assertTrue("aeiouaoaeoc".equals(StringUtils.stripAccents("áéíóúãõâêôç")));
    }

    @Test
    public void testIsNullOrEmpty () throws Exception
    {
        Assert.assertTrue(StringUtils.isNullOrEmpty(null));
        Assert.assertTrue(StringUtils.isNullOrEmpty(""));
        Assert.assertTrue(StringUtils.isNullOrEmpty(" "));
        Assert.assertTrue(StringUtils.isNullOrEmpty("  "));
        Assert.assertTrue(StringUtils.isNullOrEmpty("          "));
        Assert.assertTrue(StringUtils.isNullOrEmpty("\t"));
        Assert.assertTrue(StringUtils.isNullOrEmpty("\t\t"));
        Assert.assertTrue(StringUtils.isNullOrEmpty("\n\n"));
        Assert.assertTrue(StringUtils.isNullOrEmpty("\n\n\t "));

        Assert.assertFalse(StringUtils.isNullOrEmpty("1"));
        Assert.assertFalse(StringUtils.isNullOrEmpty("Test"));
        Assert.assertFalse(StringUtils.isNullOrEmpty("Number"));
    }
}