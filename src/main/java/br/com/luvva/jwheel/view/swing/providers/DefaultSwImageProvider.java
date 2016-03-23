package br.com.luvva.jwheel.view.swing.providers;

import br.com.luvva.jwheel.Resources;
import br.com.luvva.jwheel.cdi.utils.Initialized;
import br.com.luvva.jwheel.view.swing.utils.ResourceProvider;
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
        mainViewArt = resourceProvider.getImage(Resources.MAIN_VIEW_ART);
    }

    //<editor-fold desc="SwImageProvider">
    @Override
    public Icon questionIcon ()
    {
        return resourceProvider.getIcon(Resources.QUESTION_MARK_48X48);
    }

    @Override
    public Icon successIcon ()
    {
        return resourceProvider.getIcon(Resources.SUCCESS_64X64);
    }

    @Override
    public Icon errorIcon ()
    {
        return resourceProvider.getIcon(Resources.ERROR_64X64);
    }

    @Override
    public Icon confirmIcon ()
    {
        return resourceProvider.getIcon(Resources.OK_16X16);
    }

    @Override
    public Icon cancelIcon ()
    {
        return resourceProvider.getIcon(Resources.CANCEL_16X16);
    }

    @Override
    public Icon saveIcon ()
    {
        return resourceProvider.getIcon(Resources.SAVE_16X16);
    }

    @Override
    public Icon acButtonIcon ()
    {
        return resourceProvider.getIcon(Resources.DOWN_ARROW_8X6);
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
