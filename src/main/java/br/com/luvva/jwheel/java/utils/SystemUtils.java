package br.com.luvva.jwheel.java.utils;

import javax.inject.Singleton;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class SystemUtils
{

    public boolean isOsX ()
    {
        return System.getProperty("os.name").toLowerCase().startsWith("mac os x");
    }

    public String getLocalhostName () throws UnknownHostException
    {
        return InetAddress.getLocalHost().getHostName();
    }

}
