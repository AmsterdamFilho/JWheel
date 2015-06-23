package br.com.luvva.jwheel.swing.template.builders;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface JwFrameBuilder
{
    JMenuBar getJMenuBar ();

    JPanel getPageStartPanel ();

    List<Component> getDesktopPaneComponents ();

    JPanel getPageEndPanel ();
}
