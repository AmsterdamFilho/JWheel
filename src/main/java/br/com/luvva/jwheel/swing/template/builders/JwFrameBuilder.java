package br.com.luvva.jwheel.swing.template.builders;

import javax.swing.*;
import java.util.List;

public interface JwFrameBuilder
{
    JMenuBar getJMenuBar ();

    JComponent getPageStartPanel ();

    List<JComponent> getDesktopPaneComponents ();

    JComponent getPageEndPanel ();
}
