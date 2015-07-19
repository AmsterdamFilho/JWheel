package br.com.luvva.jwheel.view.swing.builders;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DefaultJwFrameBuilder implements JwFrameBuilder
{
    @Override
    public JMenuBar getJMenuBar ()
    {
        return null;
    }

    @Override
    public JComponent getPageStartPanel ()
    {
        return null;
    }

    @Override
    public List<JComponent> getDesktopPaneComponents ()
    {
        return new ArrayList<>();
    }

    @Override
    public JComponent getPageEndPanel ()
    {
        return null;
    }
}
