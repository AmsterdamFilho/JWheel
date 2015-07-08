package br.com.luvva.jwheel.java.utils;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class SystemUtils
{
    private boolean isOsX = System.getProperty("os.name").toLowerCase().startsWith("mac os x");

    public boolean isOsX ()
    {
        return isOsX;
    }

}
