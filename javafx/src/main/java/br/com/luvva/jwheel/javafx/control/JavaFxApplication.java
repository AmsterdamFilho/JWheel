package br.com.luvva.jwheel.javafx.control;

import br.com.luvva.jwheel.cdi.WeldContext;
import br.com.luvva.jwheel.javafx.model.i18n.TextProvider;
import br.com.luvva.jwheel.javafx.model.utils.DecisionDialogModel;
import br.com.luvva.jwheel.javafx.view.utils.AlertProducer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.net.URL;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class JavaFxApplication
{
    private @Inject Logger        logger;
    private @Inject AlertProducer alertProducer;
    private @Inject TextProvider  textProvider;

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
                new DecisionDialogModel(textProvider.getText(TextProvider.z_cs_ddmTitle),
                        textProvider.getText(TextProvider.z_cs_ddmConfigureConnectionOption),
                        textProvider.getText(TextProvider.z_cs_ddmTryAgainOption),
                        textProvider.getText(TextProvider.z_cs_ddmExitOption));
        alertProducer.showDecisionDialog(decisionDialogModel);
        return decisionDialogModel;
    }

    public void showConnectionSettingsDialog ()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(param -> WeldContext.getInstance().getBean(param));
            fxmlLoader.setResources(textProvider.getResourceBundle());
            Parent csPane = fxmlLoader.load(getClass().getResourceAsStream("/fxml/csd.fxml"));
            Stage stage = new Stage();
            stage.setTitle(textProvider.getText(TextProvider.z_cs_title));
            stage.setScene(new Scene(csPane));
            stage.showAndWait();
        }
        catch (Exception e)
        {
            alertProducer.showErrorAlert(textProvider.getText(TextProvider.z_a_internalErrorMessage));
            logger.error("Error loading Connection Settings Dialog!", e);
        }
    }

    protected String getStyleSheetResource (String resourcePath)
    {
        URL resource = getClass().getResource(resourcePath);
        if (resource == null)
        {
            return null;
        }
        else
        {
            return resource.toExternalForm();
        }
    }

    public String getRecordPaneStyleSheet ()
    {
        return getStyleSheetResource("/record-pane.css");
    }

    public abstract void init (Stage primaryStage);
}
