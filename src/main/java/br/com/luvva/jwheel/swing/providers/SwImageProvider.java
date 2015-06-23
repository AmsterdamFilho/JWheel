package br.com.luvva.jwheel.swing.providers;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
public interface SwImageProvider
{

    Icon getQuestionIcon ();

    boolean decorateMainView (Graphics g, int mainViewWidth, int mainViewHeight, ImageObserver observer);

}
