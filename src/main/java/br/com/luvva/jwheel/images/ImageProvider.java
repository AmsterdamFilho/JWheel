package br.com.luvva.jwheel.images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public interface ImageProvider
{

    Icon getQuestionIcon ();

    boolean decorateMainView (Graphics g, int mainViewWidth, int mainViewHeight, ImageObserver observer);

}
