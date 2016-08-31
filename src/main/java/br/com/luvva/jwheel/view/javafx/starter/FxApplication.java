package br.com.luvva.jwheel.view.javafx.starter;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.control.JwApplication;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        try
        {
            // shows splash if clients specified one
            Class<?> fxSplashImplClass = Class.forName("br.com.luvva.jwheel.view.javafx.starter.FxSplashImpl");
            FxSplash fxSplashImpl = (FxSplash) fxSplashImplClass.newInstance();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(fxSplashImpl.getScene());
        }
        catch (Exception ignored)
        {
        }

        primaryStage.show();

        // execute initial system configuration (JavaFX configuration, Logger, Database connection test etc.)
        JWheelStarterTask starterTask = new JWheelStarterTask();
        starterTask.setOnSucceeded(event -> testDatabaseConnection());
        Thread thread = new Thread(starterTask);
        thread.setDaemon(true);
        thread.start();
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
        showConnectionTestFailedDialogDecision(ddm);
        switch (ddm.getChosenOption())
        {
            case 0:
                // try again
                testDatabaseConnection();
                break;
            case 1:
                // show connection settings dialog
                WeldContext.getInstance().getBean(FxConnectionSettings.class).showAndWait();
                testDatabaseConnection();
                break;
            case 2:
                // exit
                primaryStage.close();
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
