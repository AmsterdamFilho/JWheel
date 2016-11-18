package br.com.jwheel.core.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleEncoderTest
{
    @Test
    public void testEncode () throws Exception
    {
        SimpleEncoder.encode("");
        SimpleEncoder.encode("101020");
        SimpleEncoder.encode("192.168.25.200");
        SimpleEncoder.encode("Apple");
        SimpleEncoder.encode("ÉéçÇÃã :!? xyz987");
    }

    @Test
    public void testDecode () throws Exception
    {
        Assert.assertTrue("".equals(SimpleEncoder.decode(SimpleEncoder.encode(""))));

        String numbersString = "101020";
        Assert.assertTrue(numbersString.equals(SimpleEncoder.decode(SimpleEncoder.encode(numbersString))));

        String ipString = "192.168.25.200";
        Assert.assertTrue(ipString.equals(SimpleEncoder.decode(SimpleEncoder.encode(ipString))));

        String simpleString = "Apple";
        Assert.assertTrue(simpleString.equals(SimpleEncoder.decode(SimpleEncoder.encode(simpleString))));

        String complexString = "ÉéçÇÃã :!? xyz987";
        Assert.assertTrue(complexString.equals(SimpleEncoder.decode(SimpleEncoder.encode(complexString))));
    }
}