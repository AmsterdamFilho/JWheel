package br.com.luvva.jwheel.swing.providers;

import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

@ApplicationScoped
public class DefaultSwImageProvider implements SwImageProvider
{

    private static final String QUESTION_MARK = "question_mark.png";
    private static final String MAIN_VIEW_ART = "main_view_art.jpg";

    private final String templateDirectory = "/template/";
    private final String imagesDirectory   = templateDirectory + "images/";

    @Inject
    private Logger logger;
    private Image  mainViewArt;

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
            return new ImageIcon(getClass().getResource(imagesDirectory + fileName));
        }
        catch (Exception ex)
        {
            logger.error("Could not create icon for image: " + fileName, ex);
            return null;
        }
    }

    private Image getImage (String path)
    {
        return Toolkit.getDefaultToolkit().getImage(getUrl(path));
    }

    private URL getUrl (String path)
    {
        return getClass().getResource(path);
    }
    //</editor-fold>

}
