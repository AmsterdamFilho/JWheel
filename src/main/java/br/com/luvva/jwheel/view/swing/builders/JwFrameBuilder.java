package br.com.luvva.jwheel.view.swing.builders;

import javax.swing.*;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface JwFrameBuilder
{
    JMenuBar getJMenuBar ();

    JComponent getPageStartPanel ();

    List<JComponent> getDesktopPaneComponents ();

    JComponent getPageEndPanel ();
}
