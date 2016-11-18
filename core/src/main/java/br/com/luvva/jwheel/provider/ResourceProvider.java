package br.com.luvva.jwheel.provider;

import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class ResourceProvider
{
    private @Inject Logger logger;
    private final   String root;

    public ResourceProvider ()
    {
        root = "/" + rootResourceDirectory() + "/";
    }

    /**
     * The root folder in the resource directory. May not be empty. May not start or end with a slash.
     *
     * @return the root directory
     */
    protected abstract String rootResourceDirectory ();

    protected InputStream getResourceInputStream (String resource)
    {
        InputStream inputStream = getClass().getResourceAsStream(resource);
        if (inputStream == null)
        {
            logger.error("Could not find the resource " + resource + "!", new NullPointerException());
        }
        return inputStream;
    }

    protected String getResourceURL (String resourcePath)
    {
        URL resource = getClass().getResource(resourcePath);
        if (resource == null)
        {
            logger.error("Could not find the resource " + resourcePath + "!", new NullPointerException());
            return null;
        }
        return resource.toExternalForm();
    }

    protected String fxmlPath ()
    {
        return root + "fxml/";
    }

    protected String cssPath ()
    {
        return root + "css/";
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
