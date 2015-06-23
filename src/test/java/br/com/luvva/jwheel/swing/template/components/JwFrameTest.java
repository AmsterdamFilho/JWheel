package br.com.luvva.jwheel.swing.template.components;

import br.com.luvva.jwheel.AbstractTest;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.swing.template.builders.JwFrameBuilder;
import br.com.luvva.jwheel.swing.utils.JMenuBarBuilder;
import br.com.luvva.jwheel.swing.utils.SwingUtils;
import org.junit.Test;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class JwFrameTest extends AbstractTest implements JwFrameBuilder
{

    @Inject
    private SwingUtils swingUtils;

    @Test
    public void testArtImage () throws InterruptedException
    {
        JwFrame bean = WeldContext.getInstance().getBean(JwFrame.class);
        bean.setVisible(true);
        Thread.sleep(10000000);
    }

    @Override
    public JMenuBar getJMenuBar ()
    {

        JMenuBarBuilder builder = new JMenuBarBuilder();
        builder.addMenu("ParentMenu", 'P');
        builder.addMenuItem("ChildMenu1", null);
        builder.addChildMenu("ChildMenu11");
        builder.addMenuItem("ChildMenu111", e -> swingUtils.getUserConfirmation("Teste"));
        builder.addMenuItem("ChildMenu112", null);
        builder.addChildMenu("ChildMenu113");
        builder.addMenuItem("ChildMenu1131", null);
        builder.addMenuItem("ChildMenu1132", null);
        builder.leaveChildMenu();
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