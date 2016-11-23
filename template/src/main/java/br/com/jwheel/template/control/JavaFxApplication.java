package br.com.jwheel.template.control;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.DecisionDialogModel;
import br.com.jwheel.javafx.fxml.CdiEnabledFxmlLoader;
import br.com.jwheel.javafx.view.AlertProducer;
import br.com.jwheel.template.view.MyResourceProvider;
import javafx.fxml.FXMLLoader;
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
    private @Inject Logger             logger;
    private @Inject AlertProducer      alertProducer;
    private @Inject MyResourceProvider resourceProvider;

    public boolean databaseConnectionOk ()
    {
        try
        {
            WeldContext.getInstance().getBean(EntityManager.class);
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
                new DecisionDialogModel(resourceProvider.getI18nProperty(MyResourceProvider.z_cs_ddmTitle),
                        resourceProvider.getI18nProperty(MyResourceProvider.z_cs_ddmConfigureConnectionOption),
                        resourceProvider.getI18nProperty(MyResourceProvider.z_cs_ddmTryAgainOption),
                        resourceProvider.getI18nProperty(MyResourceProvider.z_cs_ddmExitOption));
        alertProducer.showDecisionDialog(decisionDialogModel);
        return decisionDialogModel;
    }

    public void showConnectionSettingsDialog ()
    {
        try
        {
            FXMLLoader fxmlLoader = new CdiEnabledFxmlLoader();
            fxmlLoader.setResources(resourceProvider.getI18nBundle());
            Parent csPane = fxmlLoader.load(resourceProvider.csdFxml());
            Stage stage = new Stage();
            stage.setTitle(resourceProvider.getI18nProperty(MyResourceProvider.z_cs_title));
            stage.setScene(new Scene(csPane));
            stage.showAndWait();
        }
        catch (Exception e)
        {
            alertProducer.showErrorAlert(resourceProvider.getI18nProperty(MyResourceProvider.z_a_internalErrorMessage));
            logger.error("Error loading Connection Settings Dialog!", e);
        }
    }

    public abstract void init (Stage primaryStage);
}
