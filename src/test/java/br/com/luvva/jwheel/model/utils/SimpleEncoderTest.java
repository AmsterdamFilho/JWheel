package br.com.luvva.jwheel.model.utils;

import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleEncoderTest
{

    @Test
    public void testEncode () throws Exception
    {
        System.out.println();
        System.out.println("Encoding empty String");
        System.out.println(SimpleEncoder.encode(""));
        String numbersString = "101020";
        System.out.println("Encoding simple numbers: " + numbersString);
        System.out.println(SimpleEncoder.encode(numbersString));
        String ipString = "192.168.25.200";
        System.out.println("Encoding ip String: " + ipString);
        System.out.println(SimpleEncoder.encode(ipString));
        String simpleString = "Apple";
        System.out.println("Encoding simple String: " + simpleString);
        System.out.println(SimpleEncoder.encode(simpleString));
        String complexString = "ÉéçÇÃã :!? xyz987";
        System.out.println("Encoding complex String: " + complexString);
        System.out.println(SimpleEncoder.encode(complexString));
        System.out.println();
    }

    @Test
    public void testDecode () throws Exception
    {
        System.out.println();
        System.out.println("Testing decoding of empty String");
        System.out.println("".equals(SimpleEncoder.decode(SimpleEncoder.encode(""))) ? "OK" : "Not ok");
        String numbersString = "101020";
        System.out.println("Testing decoding of numbers String: " + numbersString);
        System.out.println(numbersString.equals(SimpleEncoder.decode(SimpleEncoder.encode(numbersString))) ? "OK" : "Not ok");
        String ipString = "192.168.25.200";
        System.out.println("Testing decoding of ip String: " + ipString);
        System.out.println(ipString.equals(SimpleEncoder.decode(SimpleEncoder.encode(ipString))) ? "OK" : "Not ok");
        String simpleString = "Apple";
        System.out.println("Testing decoding of simple String: " + simpleString);
        System.out.println(simpleString.equals(SimpleEncoder.decode(SimpleEncoder.encode(simpleString))) ? "OK" : "Not ok");
        String complexString = "ÉéçÇÃã :!? xyz987";
        System.out.println("Testing decoding of complex String: " + complexString);
        System.out.println(complexString.equals(SimpleEncoder.decode(SimpleEncoder.encode(complexString))) ? "OK" : "Not ok");
        System.out.println();
    }
}