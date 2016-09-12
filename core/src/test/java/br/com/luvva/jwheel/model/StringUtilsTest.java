package br.com.luvva.jwheel.model;

import org.junit.Assert;
import org.junit.Test;

import static br.com.luvva.jwheel.model.StringUtils.isNullOrEmpty;
import static br.com.luvva.jwheel.model.StringUtils.stripAccents;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class StringUtilsTest
{
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