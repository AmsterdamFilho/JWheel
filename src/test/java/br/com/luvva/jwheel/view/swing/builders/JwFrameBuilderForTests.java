package br.com.luvva.jwheel.view.swing.builders;

import br.com.luvva.jwheel.view.swing.providers.ImageProvider;
import br.com.luvva.jwheel.view.swing.utils.SwingUtils;
import org.slf4j.Logger;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
public class JwFrameBuilderForTests extends DefaultJwFrameBuilder
{

    private @Inject SwingUtils    swingUtils;
    private @Inject ImageProvider imageProvider;
    private @Inject Logger        logger;

    @Override
    public JMenuBar getJMenuBar ()
    {
        JMenuBarBuilder builder = new JMenuBarBuilder();
        builder.addMenu("ParentMenu", 'P');
        builder.addMenuItem("ChildMenu1", null);
        builder.addChildMenu("ChildMenu11");
        builder.addMenuItem("ChildMenu111", e -> swingUtils.getUserConfirmation("New test"));
        builder.addMenuItem("ChildMenu112", e -> logger.warn("ok"));
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

        builder.addNewButton("Appointment", "Schedule appointment",
                new Rectangle(10, 10, 145, 60),
                imageProvider.getAppointmentIcon(),
                e -> {
                });
        builder.addNewButton("Patients", "Patients records",
                new Rectangle(15, 75, 135, 60),
                imageProvider.getPatientIcon(),
                e -> {
                });
        builder.addNewButton("APPRAISAL", null,
                new Rectangle(15, 140, 135, 60),
                imageProvider.getAppraisalIcon(),
                e -> {
                });
        builder.addNewButton("CAMERA", "ACCESS CAMERA",
                new Rectangle(15, 205, 135, 60),
                imageProvider.getCameraIcon(),
                e -> swingUtils.getUserConfirmation("False"));
        builder.addNewButton("Reports", "Generate reports",
                new Rectangle(15, 270, 135, 60),
                e -> {
                });
        builder.addNewButton("CID10",
                new Rectangle(15, 335, 135, 60),
                imageProvider.getCidIcon(),
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
