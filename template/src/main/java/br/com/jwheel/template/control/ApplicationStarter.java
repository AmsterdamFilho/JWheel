package br.com.jwheel.template.control;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.model.view.DecisionDialogModel;
import br.com.jwheel.logging.JwLoggerFactory;
import br.com.jwheel.javafx.utils.JavaFxUtils;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ApplicationStarter extends Application
{
    private Stage             primaryStage;
    private JavaFxApplication javaFxApplication;

    @Override
    public void start (Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;

        showSplash();

        // execute initial system configuration (JavaFX configuration, Logger, Database connection test etc.)
        JWheelStarterTask starterTask = new JWheelStarterTask();
        starterTask.setOnSucceeded(event -> testDatabaseConnection());
        starterTask.setOnFailed(event ->
        {
            //noinspection ThrowableResultOfMethodCallIgnored
            event.getSource().getException().printStackTrace();
            primaryStage.close();
        });
        new Thread(starterTask).start();
    }

    /**
     * Shows splash if client specified one. Clients should create a shaded jar and add splash.fxml to its root resource
     * directory. A splash.properties resource bundle in the same directory is also used if provided. If JWheel is used
     * as a jar (client did not create a shaded jar), it will not work, unless client extends this class and runs launch
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
            JavaFxUtils.centerOnScreen(primaryStage);
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
                    javaFxApplication.init(primaryStage);
                }
                else
                {
                    DecisionDialogModel ddm = javaFxApplication.showConnectionTestFailedDialogDecision();
                    switch (ddm.getChosenOption())
                    {
                        case 0:
                            // show connection settings dialog
                            javaFxApplication.showConnectionSettingsDialog();
                            testDatabaseConnection();
                            break;
                        case 1:
                            // try again
                            testDatabaseConnection();
                            break;
                        case 2:
                            // give up
                            primaryStage.close();
                            break;
                        default:
                            assert false : "Connection test failed decision invalid!";
                    }
                }
            }
            catch (InterruptedException | ExecutionException ignored)
            {
            }
        });
        new Thread(starterTask).start();
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
            WeldContext.getInstance().getBean(JwLoggerFactory.class).init();
            javaFxApplication = WeldContext.getInstance().getBean(JavaFxApplication.class);
            return null;
        }
    }

    private class ConnectionTestTask extends Task<Boolean>
    {
        @Override
        protected Boolean call () throws Exception
        {
            return javaFxApplication.databaseConnectionOk();
        }
    }
}
