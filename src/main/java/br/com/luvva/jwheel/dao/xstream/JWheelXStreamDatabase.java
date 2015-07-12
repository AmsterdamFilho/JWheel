package br.com.luvva.jwheel.dao.xstream;

import br.com.luvva.jwheel.JWheel;

import java.io.File;
import java.nio.file.FileSystems;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JWheelXStreamDatabase implements XStreamDatabase
{

    @Override
    public File getFile (String relativePath)
    {
        String separator = FileSystems.getDefault().getSeparator();
        return new File(getDefaultDatabaseDirectory() + separator +
                getProductName() + separator + relativePath + "." + getExtension());
    }

    protected String getExtension ()
    {
        return "xml";
    }

    protected String getDefaultDatabaseDirectory ()
    {
        return System.getProperty("user.home");
    }

    protected String getProductName ()
    {
        return JWheel.NAME;
    }

}
