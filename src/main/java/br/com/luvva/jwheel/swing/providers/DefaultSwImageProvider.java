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
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@ApplicationScoped
public class DefaultSwImageProvider implements SwImageProvider
{

    private static final String QUESTION_MARK = "question_mark.png";
    private static final String MAIN_VIEW_ART = "main_view_art.jpg";

    private @Inject Logger logger;
    private         Image  mainViewArt;

    public DefaultSwImageProvider ()
    {
        mainViewArt = getImage(MAIN_VIEW_ART);
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
            //noinspection ConstantConditions
            return new ImageIcon(getImageResource(fileName));
        }
        catch (Exception ex)
        {
            logger.error("Could not get icon resource: " + fileName, ex);
            return null;
        }
    }

    private Image getImage (String fileName)
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
        try
        {
            return getClass().getResource("/template/swing/images/" + fileName);
        }
        catch (Exception ex)
        {
            logger.error("Could not get resource: " + fileName, ex);
            return null;
        }
    }
    //</editor-fold>

}
