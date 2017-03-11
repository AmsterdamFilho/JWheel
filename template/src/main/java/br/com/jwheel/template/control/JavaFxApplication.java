package br.com.jwheel.template.control;

import br.com.jwheel.core.model.view.DecisionDialogModel;
import br.com.jwheel.core.service.cdi.WeldContext;
import br.com.jwheel.javafx.laf.DialogProducer;
import br.com.jwheel.javafx.utils.JwFxmlLoader;
import br.com.jwheel.template.JwTemplateResourceProvider;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class JavaFxApplication
{
    private @Inject Logger                     logger;
    private @Inject DialogProducer             dialogProducer;
    private @Inject JwTemplateResourceProvider resourceProvider;

    public boolean databaseConnectionOk ()
    {
        try
        {
            WeldContext.getInstance().getAny(EntityManager.class);
            return true;
        }
        catch (Exception ex)
        {
            logger.error("Could not create an EntityManager!", ex);
            return false;
        }
    }

    public DecisionDialogModel showConnectionTestFailedDialogDecision ()
    {
        DecisionDialogModel decisionDialogModel =
                new DecisionDialogModel(resourceProvider.getI18nProperty(JwTemplateResourceProvider.z_cs_ddmTitle),
                        resourceProvider.getI18nProperty(JwTemplateResourceProvider.z_cs_ddmConfigureConnectionOption),
                        resourceProvider.getI18nProperty(JwTemplateResourceProvider.z_cs_ddmTryAgainOption),
                        resourceProvider.getI18nProperty(JwTemplateResourceProvider.z_cs_ddmExitOption));
        dialogProducer.showDecisionDialog(decisionDialogModel);
        return decisionDialogModel;
    }

    public void showConnectionSettingsDialog ()
    {
        try
        {
            Stage stage = new Stage();
            stage.setTitle(resourceProvider.getI18nProperty(JwTemplateResourceProvider.z_cs_title));
            Parent csPane = JwFxmlLoader.loadWithCdi(resourceProvider.csdFxml(), resourceProvider.getI18nBundle());
            stage.setScene(new Scene(csPane));
            stage.showAndWait();
        }
        catch (Exception e)
        {
            dialogProducer.showErrorAlert(resourceProvider.getI18nProperty(JwTemplateResourceProvider
                    .z_a_internalErrorMessage));
            logger.error("Error loading Connection Settings Dialog!", e);
        }
    }

    public abstract void init (Stage primaryStage);
}
