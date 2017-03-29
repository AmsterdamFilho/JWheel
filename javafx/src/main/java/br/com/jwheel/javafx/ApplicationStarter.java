package br.com.jwheel.javafx;

import br.com.jwheel.weld.WeldContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ApplicationStarter extends Application
{
    private Stage             primaryStage;
    private JavaFxApplication application;

    @Override
    public void start (Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;

        showSplash();

        JWheelStarterTask starterTask = new JWheelStarterTask();
        starterTask.setOnSucceeded(event -> Platform.runLater(() -> application.init(primaryStage)));
        starterTask.setOnFailed(event ->
        {
            //noinspection ThrowableResultOfMethodCallIgnored
            event.getSource().getException().printStackTrace();
            primaryStage.close();
        });
        new Thread(starterTask, "JWheel JavaFX application starter thread").start();
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
            primaryStage.centerOnScreen();
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

    private class JWheelStarterTask extends Task<Void>
    {
        @Override
        protected Void call () throws Exception
        {
            application = WeldContext.getInstance().getAny(JavaFxApplication.class);
            return null;
        }
    }

    @Override
    public void stop () throws Exception
    {
        if (application != null)
        {
            application.stop();
        }
    }

    public static void main (String[] args)
    {
        launch(args);
    }
}
