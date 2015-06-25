package br.com.luvva.jwheel.swing.providers;

import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@ApplicationScoped
public class DefaultSwImageProvider implements SwImageProvider
{

    private static final String QUESTION_MARK = "question_mark.png";
    private static final String MAIN_VIEW_ART = "main_view_art.jpg";

    private final String templateDirectory = "/template/";
    private final String imagesDirectory   = templateDirectory + "images/";

    private @Inject Logger logger;
    private         Image  mainViewArt;

    public DefaultSwImageProvider ()
    {
        mainViewArt = getImage(imagesDirectory + MAIN_VIEW_ART);
    }

    public DefaultSwImageProvider (Image mainViewArt)
    {
        this.mainViewArt = mainViewArt;
    }

    public void setMainViewArt (Image mainViewArt)
    {
        this.mainViewArt = mainViewArt;
    }

    //<editor-fold desc="SwImageProvider">
    @Override
    public Icon getQuestionIcon ()
    {
        return getIcon(QUESTION_MARK);
    }

    @Override
    public boolean decorateMainView (Graphics g, int mainViewWidth, int mainViewHeight, ImageObserver observer)
    {
        if (mainViewArt == null)
        {
            return false;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(mainViewArt, 0, 0, mainViewWidth, mainViewHeight, observer);
        g2d.dispose();
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Private">
    private Icon getIcon (String fileName)
    {
        try
        {
            return new ImageIcon(getResource(imagesDirectory + fileName));
        }
        catch (Exception ex)
        {
            logger.error("Could not get icon resource: {}", ex, fileName);
            return null;
        }
    }

    private Image getImage (String fileName)
    {
        try
        {
            return Toolkit.getDefaultToolkit().getImage(getResource(imagesDirectory + fileName));
        }
        catch (Exception ex)
        {
            logger.error("Could not get image resource: {}", ex, fileName);
            return null;
        }
    }

    private URL getResource (String fileName)
    {
        try
        {
            return getClass().getResource(fileName);
        }
        catch (Exception ex)
        {
            logger.error("Could not get resource: {}", ex, fileName);
            return null;
        }
    }
    //</editor-fold>

}
