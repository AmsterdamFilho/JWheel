package br.com.jwheel.javafx.extension;

import br.com.jwheel.javafx.JWheelFxmlLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class ExtensionDemo extends Application
{
    @Override
    public void start (Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new JWheelFxmlLoader(getFxml());
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.centerOnScreen();
        primaryStage.setTitle(title());
        primaryStage.show();
    }

    abstract String title ();

    abstract URL getFxml ();
}
