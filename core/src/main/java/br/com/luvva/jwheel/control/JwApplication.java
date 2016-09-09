package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.model.i18n.TextProvider;
import br.com.luvva.jwheel.view.javafx.utils.AlertProducer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwApplication
{
    private @Inject JwLoggerFactory  jwLoggerFactory;
    private @Inject Logger           logger;
    private @Inject LogParameters    logParameters;
    private @Inject ConnectionTester connectionTester;
    private @Inject AlertProducer    alertProducer;
    private @Inject TextProvider     textProvider;

    public void configureLogger ()
    {
        jwLoggerFactory.configure(logParameters);
    }

    public boolean databaseConnectionOk ()
    {
        return connectionTester.execute();
    }

    public DecisionDialogModel showConnectionTestFailedDialogDecision ()
    {
        DecisionDialogModel decisionDialogModel =
                new DecisionDialogModel(textProvider.getText(TextProvider.cs_ddmTitle),
                        textProvider.getText(TextProvider.cs_ddmConfigureConnection),
                        textProvider.getText(TextProvider.cs_ddmTryAgain),
                        textProvider.getText(TextProvider.cs_ddmExit));
        alertProducer.showDecisionDialog(decisionDialogModel);
        return decisionDialogModel;
    }

    public void init (Stage primaryStage)
    {
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
            stage.setTitle(textProvider.getText(TextProvider.cs_title));
            stage.setScene(new Scene(csPane));
            stage.showAndWait();
        }
        catch (Exception e)
        {
            alertProducer.showErrorAlert(textProvider.getText(TextProvider.g_internalError));
            logger.error("Error loading Connection Settings Dialog!", e);
        }
    }
}
