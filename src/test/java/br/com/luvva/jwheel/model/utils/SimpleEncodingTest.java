package br.com.luvva.jwheel.model.utils;

import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleEncodingTest
{

    @Test
    public void testEncode () throws Exception
    {
        System.out.println();
        System.out.println("Encoding empty String");
        System.out.println(SimpleEncoding.encode(""));
        String numbersString = "101020";
        System.out.println("Encoding simple numbers: " + numbersString);
        System.out.println(SimpleEncoding.encode(numbersString));
        String ipString = "192.168.25.200";
        System.out.println("Encoding ip String: " + ipString);
        System.out.println(SimpleEncoding.encode(ipString));
        String simpleString = "Apple";
        System.out.println("Encoding simple String: " + simpleString);
        System.out.println(SimpleEncoding.encode(simpleString));
        String complexString = "ÉéçÇÃã :!? xyz987";
        System.out.println("Encoding complex String: " + complexString);
        System.out.println(SimpleEncoding.encode(complexString));
        System.out.println();
    }

    @Test
    public void testDecode () throws Exception
    {
        System.out.println();
        System.out.println("Testing decoding of empty String");
        System.out.println("".equals(SimpleEncoding.decode(SimpleEncoding.encode(""))) ? "OK" : "Not ok");
        String numbersString = "101020";
        System.out.println("Testing decoding of numbers String: " + numbersString);
        System.out.println(numbersString.equals(SimpleEncoding.decode(SimpleEncoding.encode(numbersString))) ? "OK" : "Not ok");
        String ipString = "192.168.25.200";
        System.out.println("Testing decoding of ip String: " + ipString);
        System.out.println(ipString.equals(SimpleEncoding.decode(SimpleEncoding.encode(ipString))) ? "OK" : "Not ok");
        String simpleString = "Apple";
        System.out.println("Testing decoding of simple String: " + simpleString);
        System.out.println(simpleString.equals(SimpleEncoding.decode(SimpleEncoding.encode(simpleString))) ? "OK" : "Not ok");
        String complexString = "ÉéçÇÃã :!? xyz987";
        System.out.println("Testing decoding of complex String: " + complexString);
        System.out.println(complexString.equals(SimpleEncoding.decode(SimpleEncoding.encode(complexString))) ? "OK" : "Not ok");
        System.out.println();
    }
}