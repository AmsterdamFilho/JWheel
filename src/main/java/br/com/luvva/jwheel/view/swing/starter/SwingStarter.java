package br.com.luvva.jwheel.view.swing.starter;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.view.interfaces.ViewStarter;
import br.com.luvva.jwheel.view.swing.components.SwDecisionDialog;
import br.com.luvva.jwheel.view.swing.utils.InvokeAndWaitHandler;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Font;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SwingStarter implements ViewStarter
{

    private @Inject Logger               logger;
    private @Inject TextProvider         textProvider;
    private @Inject InvokeAndWaitHandler invokeAndWaitHandler;

    private JDialog connectionTestProgressDialog;

    @Override
    public void configureView ()
    {
        java.awt.EventQueue.invokeLater(() -> {
            LookAndFeel laf = new NimbusLookAndFeel();
            laf.getDefaults().put("ProgressBarUI", "javax.swing.plaf.basic.BasicProgressBarUI");
            laf.getDefaults().put("ProgressBar.cycleTime", 2500);
            laf.getDefaults().put("PopupMenu.consumeEventOnClose", Boolean.FALSE);
            laf.getDefaults().put("ToolTip.font", new FontUIResource(
                    new FontUIResource("SansSerif", Font.PLAIN, 14)));
            try
            {
                UIManager.setLookAndFeel(laf);
            }
            catch (UnsupportedLookAndFeelException e)
            {
                logger.warn("Exception while setting look and feel!", e);
            }
        });
    }

    @Override
    public void showConnectionTestProgressDialog ()
    {
        java.awt.EventQueue.invokeLater(() -> {
            connectionTestProgressDialog = WeldContext.getInstance().getBean(ConnectionTestProgressDialog.class);
            connectionTestProgressDialog.setVisible(true);
        });
    }

    @Override
    public void closeConnectionTestProgressDialog ()
    {
        if (connectionTestProgressDialog != null)
        {
            java.awt.EventQueue.invokeLater(() -> connectionTestProgressDialog.dispose());
        }
    }

    @Override
    public void showConnectionTestFailedDialogDecision (DecisionDialogModel decisionDialogModel)
    {
        invokeAndWaitHandler.handle(() -> {
            SwDecisionDialog swDecisionDialog = WeldContext.getInstance().getBean(SwDecisionDialog.class);
            swDecisionDialog.setDecisionDialogModel(decisionDialogModel);
            swDecisionDialog.setModal(true);
            swDecisionDialog.setVisible(true);
        });
    }
}
