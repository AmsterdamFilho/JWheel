package br.com.luvva.jwheel.template.swing.builders;

import javax.swing.*;
import java.util.List;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
public interface JwFrameBuilder
{
    JMenuBar getJMenuBar ();

    JComponent getPageStartPanel ();

    List<JComponent> getDesktopPaneComponents ();

    JComponent getPageEndPanel ();
}
