package br.com.luvva.jwheel.swing.providers;

import br.com.luvva.jwheel.cdi.utils.Initialized;
import br.com.luvva.jwheel.swing.utils.ResourceProvider;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class DefaultSwImageProvider implements SwImageProvider
{

    private @Inject Logger logger;
    private         Image  mainViewArt;

    @Inject
    @Initialized (stringValue = "/template/swing/images/")
    private ResourceProvider resourceProvider;

    @PostConstruct
    public void init ()
    {
        mainViewArt = resourceProvider.getImage("main_view_art.jpg");
    }

    //<editor-fold desc="SwImageProvider">
    @Override
    public Icon getQuestionIcon ()
    {
        return resourceProvider.getIcon("question_mark.png");
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

}
