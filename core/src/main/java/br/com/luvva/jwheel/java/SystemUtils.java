package br.com.luvva.jwheel.java;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SystemUtils
{
    public static boolean isOsX ()
    {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    public static boolean isWindows ()
    {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    public static String getLocalhostName () throws UnknownHostException
    {
        return InetAddress.getLocalHost().getHostName();
    }
}
