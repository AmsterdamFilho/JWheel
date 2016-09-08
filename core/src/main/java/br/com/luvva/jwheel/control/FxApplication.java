package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.view.javafx.utils.FxUtils;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FxApplication extends Application
{
    private Stage         primaryStage;
    private JwApplication jwApplication;

    @Override
    public void start (Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;

        showSplash();

        // execute initial system configuration (JavaFX configuration, Logger, Database connection test etc.)
        JWheelStarterTask starterTask = new JWheelStarterTask();
        starterTask.setOnSucceeded(event -> testDatabaseConnection());
        Thread thread = new Thread(starterTask);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Shows splash if client specified one. Clients should create an uber jar and add splash.fxml to its root resource
     * directory. A splash.properties resource bundle in the same directory is also used if provided. If JWheel is used
     * as a jar (client did not create an uber jar), it will not work, unless client extends this class and runs launch
     * method in it.
     */
    private void showSplash ()
    {
        ResourceBundle resourceBundle = null;
        try
        {
            resourceBundle = ResourceBundle.getBundle("splash", Locale.getDefault());
        }
        catch (MissingResourceException ignored)
        {
            System.out.println("Info: file splash.properties not found in resources root directory.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        if (resourceBundle != null)
        {
            fxmlLoader.setResources(resourceBundle);
        }
        try
        {
            primaryStage.setScene(new Scene(fxmlLoader.load(getClass().getResourceAsStream("/splash.fxml"))));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            FxUtils.centerOnScreen(primaryStage);
            primaryStage.show();

        }
        catch (NullPointerException ignored)
        {
            System.out.println("Info: file splash.fxml not found in resources root directory.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void testDatabaseConnection ()
    {
        ConnectionTestTask starterTask = new ConnectionTestTask();
        starterTask.setOnSucceeded(event -> {
            try
            {
                if (starterTask.get())
                {
                    jwApplication.init(primaryStage);
                }
                else
                {
                    handleFailedTestConnection();
                }
            }
            catch (InterruptedException | ExecutionException ignored)
            {
            }
        });
        Thread thread = new Thread(starterTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void handleFailedTestConnection ()
    {
        DecisionDialogModel ddm = jwApplication.getConnectionTestFailedDecisionModel();
        primaryStage.close();
        showConnectionTestFailedDialogDecision(ddm);
        switch (ddm.getChosenOption())
        {
            case 0:
                // try again
                testDatabaseConnection();
                break;
            case 1:
                // show connection settings dialog
                jwApplication.showConnectionSettingsDialog();
                testDatabaseConnection();
                break;
            case 2:
                // give up
                break;
            default:
                assert false : "Connection test failed decision invalid!";
        }
    }

    private void showConnectionTestFailedDialogDecision (DecisionDialogModel ddm)
    {
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(ddm.getDefaultOption(), ddm.getExtraOptions());
        choiceDialog.setContentText(ddm.getDecisionDescription());
        choiceDialog.showAndWait();
        ddm.setChosenOption(choiceDialog.getSelectedItem());
    }

    public static void main (String[] args)
    {
        launch(args);
    }

    private class JWheelStarterTask extends Task<Void>
    {
        @Override
        protected Void call () throws Exception
        {
            jwApplication = WeldContext.getInstance().getBean(JwApplication.class);
            jwApplication.configureJavaFX();
            jwApplication.configureLogger();
            return null;
        }
    }

    private class ConnectionTestTask extends Task<Boolean>
    {
        @Override
        protected Boolean call () throws Exception
        {
            return jwApplication.databaseConnectionOk();
        }
    }
}
