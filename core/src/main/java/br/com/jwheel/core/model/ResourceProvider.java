package br.com.jwheel.core.model;

import java.io.InputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * An utility class to get resources. The default structure is:
 *
 * |[root]
 * |    css
 * |        file1.css
 * |        file2.css
 * |        ...
 * |    images
 * |        image1.png
 * |        image2.png
 * |        ...
 * |    fxml
 * |        file1.fxml
 * |        file2.fxml
 * |        ...
 * |    properties
 * |        i18n.properties
 * |        i18nFr.properties
 * |        ...
 * |        anotherBundle.properties
 * |        ...
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class ResourceProvider
{
    /**
     * The root folder in the resource directory. May not be an empty String. May not start or end with a slash.
     *
     * @return the root directory
     */
    protected abstract String root ();

    public ResourceBundle getI18nBundle ()
    {
        try
        {
            return ResourceBundle.getBundle(propertiesPath() + "i18n");
        }
        catch (MissingResourceException e)
        {
            return null;
        }
    }

    public String getI18nProperty (String property) throws NullPointerException, MissingResourceException
    {
        try
        {
            return getI18nBundle().getString(property);
        }
        catch (NullPointerException | MissingResourceException e)
        {
            return "<" + (property == null ? "" : property) + ">";
        }
    }

    protected InputStream getFxml (String fxmlFileName)
    {
        return getClass().getResourceAsStream("/" + fxmlDirectoryPath() + fxmlFileName + fxmlExtension());
    }

    protected String getCss (String cssFileName)
    {
        URL resource = getClass().getResource("/" + cssDirectoryPath() + cssFileName + cssExtension());
        if (resource == null)
        {
            return null;
        }
        return resource.toExternalForm();
    }

    protected String propertiesPath ()
    {
        return root() + "/properties/";
    }

    protected String fxmlDirectoryPath ()
    {
        return root() + "/fxml/";
    }

    protected String cssDirectoryPath ()
    {
        return root() + "/css/";
    }

    protected String imagesDirectoryPath ()
    {
        return root() + "/images/";
    }

    protected String fxmlExtension ()
    {
        return ".fxml";
    }

    protected String cssExtension ()
    {
        return ".css";
    }
}
