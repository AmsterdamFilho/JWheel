package br.com.jwheel.utils;

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
        System.out.println(SimpleEncoder.encode(null));
        System.out.println(SimpleEncoder.encode(""));
        System.out.println(SimpleEncoder.encode("101020"));
        System.out.println(SimpleEncoder.encode("192.168.25.200"));
        System.out.println(SimpleEncoder.encode("Apple"));
        System.out.println(SimpleEncoder.encode("ÉéçÇÃã :!? xyz987"));
    }

    @Test
    public void testDecode () throws Exception
    {
        Assert.assertTrue("".equals(SimpleEncoder.decode(null)));

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