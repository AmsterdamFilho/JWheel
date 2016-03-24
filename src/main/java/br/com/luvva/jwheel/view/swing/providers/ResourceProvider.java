package br.com.luvva.jwheel.view.swing.providers;

import br.com.luvva.jwheel.cdi.utils.Initialized;
import br.com.luvva.jwheel.cdi.utils.InjectionUtils;
import org.slf4j.Logger;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Initialized
public class ResourceProvider
{

    private         String imageResourceDirectory;
    private @Inject Logger logger;

    @Inject
    public ResourceProvider (InjectionPoint ip)
    {
        imageResourceDirectory = InjectionUtils.getString(ip);
    }

    public Icon getIcon (String fileName)
    {
        try
        {
            return new ImageIcon(getImageResource(fileName));
        }
        catch (Exception ex)
        {
            logger.error("Could not get icon resource: " + fileName, ex);
            return null;
        }
    }

    public Image getImage (String fileName)
    {
        try
        {
            return Toolkit.getDefaultToolkit().getImage(getImageResource(fileName));
        }
        catch (Exception ex)
        {
            logger.error("Could not get image resource: " + fileName, ex);
            return null;
        }
    }

    private URL getImageResource (String fileName)
    {
        return getResource(imageResourceDirectory + fileName);
    }

    private URL getResource (String fileName)
    {
        try
        {
            return getClass().getResource(fileName);
        }
        catch (Exception ex)
        {
            logger.error("Could not get resource: " + fileName, ex);
            return null;
        }
    }

}
