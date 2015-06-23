package br.com.luvva.jwheel.swing.template.components;

import br.com.luvva.jwheel.AbstractTest;
import br.com.luvva.jwheel.ImageResources;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.swing.template.builders.JwFrameBuilder;
import br.com.luvva.jwheel.swing.utils.AnimatedButtonsBuilder;
import br.com.luvva.jwheel.swing.utils.JMenuBarBuilder;
import br.com.luvva.jwheel.swing.utils.SwingUtils;
import org.junit.Test;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class JwFrameTest extends AbstractTest implements JwFrameBuilder
{

    @Inject
    private SwingUtils swingUtils;

    private ImageResources imageResources = new ImageResources();

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
    public JComponent getPageStartPanel ()
    {
        return null;
    }

    @Override
    public List<JComponent> getDesktopPaneComponents ()
    {
        AnimatedButtonsBuilder builder = new AnimatedButtonsBuilder();

        builder.addNewButton("Iniciar Atendimento", "Atendimento (Ctrl+Alt+A)",
                new Rectangle(10, 10, 145, 60),
                imageResources.getIcon(ImageResources.ATENDIMENTO),
                e -> {
                });
        builder.addNewButton("Pacientes/Histórico", "Pacientes/Histórico (Ctrl+Alt+P)",
                new Rectangle(15, 75, 135, 60),
                imageResources.getIcon(ImageResources.PACIENTE),
                e -> {
                });
        builder.addNewButton("Laudos/Fotos", null,
                new Rectangle(15, 140, 135, 60),
                imageResources.getIcon(ImageResources.LAUDO),
                e -> {
                });
        builder.addNewButton("Captura", "Captura (Ctrl+Alt+C)",
                new Rectangle(15, 205, 135, 60),
                imageResources.getIcon(ImageResources.CAPTURA),
                e -> swingUtils.getUserConfirmation("False"));
        builder.addNewButton("Relatórios", "Todos os Relatórios (Ctrl+Alt+R)",
                new Rectangle(15, 270, 135, 60),
                e -> {
                });
        builder.addNewButton("CID10",
                new Rectangle(15, 335, 135, 60),
                imageResources.getIcon(ImageResources.CID),
                e -> {
                });

        return new ArrayList<>(builder.getButtons());
    }

    @Override
    public JComponent getPageEndPanel ()
    {
        return null;
    }
}