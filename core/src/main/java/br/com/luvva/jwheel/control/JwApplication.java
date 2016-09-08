package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.view.javafx.utils.AlertProducer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwApplication
{
    private @Inject JwLoggerFactory  jwLoggerFactory;
    private @Inject Logger           logger;
    private @Inject TextProvider     textProvider;
    private @Inject LogParameters    logParameters;
    private @Inject ConnectionTester connectionTester;
    private @Inject AlertProducer    alertProducer;

    public void configureJavaFX ()
    {
    }

    public void configureLogger ()
    {
        jwLoggerFactory.configure(logParameters);
    }

    public boolean databaseConnectionOk ()
    {
        return connectionTester.execute();
    }

    public DecisionDialogModel getConnectionTestFailedDecisionModel ()
    {
        return textProvider.connectionTestFailedDecision();
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
            ResourceBundle bundle = ResourceBundle.getBundle("locale/jw-locale", Locale.getDefault());
            fxmlLoader.setResources(bundle);
            Parent csdPane = fxmlLoader.load(getClass().getResourceAsStream("/fxml/csd.fxml"));
            Stage stage = new Stage();
            stage.setTitle(bundle.getString("csd-title"));
            stage.setScene(new Scene(csdPane));
            stage.showAndWait();
        }
        catch (Exception e)
        {
            alertProducer.showInternalError();
            logger.error("Error loading Connection Settings Dialog!", e);
        }
    }
}
