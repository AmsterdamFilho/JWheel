package br.com.luvva.jwheel.swing.template.crud;

import javax.swing.*;
import java.awt.*;

public interface JwFrameBuilder
{
    JMenuBar getJMenuBar ();

    JPanel getPageStartPanel ();

    Component[] getDesktopPaneComponents ();

    JPanel getPageEndPanel ();
}
