package br.com.luvva.jwheel.swing.template.crud;

import br.com.luvva.jwheel.swing.utils.JMenuBarBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.swing.*;
import java.awt.*;

@ApplicationScoped
public class Builder implements JwFrameBuilder
{
    @Override
    public JMenuBar getJMenuBar ()
    {
        JMenuBarBuilder builder = new JMenuBarBuilder();
        builder.addMenu("ParentMenu", 'P');
        builder.addMenuItem("ChildMenu1", null);
        builder.addChildMenu("ChildMenu11");
        builder.addMenuItem("ChildMenu111", null);
        builder.addMenuItem("ChildMenu112", null);
        builder.leaveChildMenu();
        builder.leaveChildMenu();
        builder.addMenu("ChildMenu2");
        builder.alignNextToTheRight();
        builder.addMenu("Help");
        builder.addMenuItem("Contact", null);
        builder.addMenuItem("Manual", null);
        return builder.getMenuBar();
    }

    @Override
    public JPanel getPageStartPanel ()
    {
        return null;
    }

    @Override
    public Component[] getDesktopPaneComponents ()
    {
        return new Component[0];
    }

    @Override
    public JPanel getPageEndPanel ()
    {
        return null;
    }
}
