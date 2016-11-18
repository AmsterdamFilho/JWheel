package br.com.luvva.jwheel.template.control;

import br.com.luvva.jwheel.cdi.WeldContext;
import br.com.luvva.jwheel.javafx.control.AlertProducer;
import br.com.luvva.jwheel.javafx.fxml.CdiEnabledFxmlLoader;
import br.com.luvva.jwheel.model.DecisionDialogModel;
import br.com.luvva.jwheel.template.provider.MyTextProvider;
import br.com.luvva.jwheel.template.view.MyResourceProvider;
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
    private @Inject MyTextProvider     textProvider;
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
                new DecisionDialogModel(textProvider.getText(MyTextProvider.z_cs_ddmTitle),
                        textProvider.getText(MyTextProvider.z_cs_ddmConfigureConnectionOption),
                        textProvider.getText(MyTextProvider.z_cs_ddmTryAgainOption),
                        textProvider.getText(MyTextProvider.z_cs_ddmExitOption));
        alertProducer.showDecisionDialog(decisionDialogModel);
        return decisionDialogModel;
    }

    public void showConnectionSettingsDialog ()
    {
        try
        {
            FXMLLoader fxmlLoader = new CdiEnabledFxmlLoader();
            fxmlLoader.setResources(textProvider.getResourceBundle());
            Parent csPane = fxmlLoader.load(resourceProvider.csdFxml());
            Stage stage = new Stage();
            stage.setTitle(textProvider.getText(MyTextProvider.z_cs_title));
            stage.setScene(new Scene(csPane));
            stage.showAndWait();
        }
        catch (Exception e)
        {
            alertProducer.showErrorAlert(textProvider.getText(MyTextProvider.z_a_internalErrorMessage));
            logger.error("Error loading Connection Settings Dialog!", e);
        }
    }

    public abstract void init (Stage primaryStage);
}
