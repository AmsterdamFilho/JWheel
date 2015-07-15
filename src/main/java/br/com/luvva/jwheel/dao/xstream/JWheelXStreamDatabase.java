package br.com.luvva.jwheel.dao.xstream;

import br.com.luvva.jwheel.model.beans.ProductData;

import javax.inject.Inject;
import java.io.File;
import java.nio.file.FileSystems;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JWheelXStreamDatabase implements XStreamDatabase
{

    private @Inject ProductData productData;
    private final String separator = FileSystems.getDefault().getSeparator();

    @Override
    public File getFile (String relativePath)
    {
        return new File(getDefaultDatabaseDirectory() + separator + relativePath + "." + getExtension());
    }

    protected String getExtension ()
    {
        return "xml";
    }

    protected String getDefaultDatabaseDirectory ()
    {
        return System.getProperty("user.home") + separator + productData.getProductName() + separator + "preferences";
    }
}
