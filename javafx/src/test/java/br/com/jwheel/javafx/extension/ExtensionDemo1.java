package br.com.jwheel.javafx.extension;

import br.com.jwheel.javafx.utils.JavaFxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ExtensionDemo1 extends Application
{
    @Override
    public void start (Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        primaryStage.setScene(new Scene(fxmlLoader.load(getClass().getResourceAsStream("/fxml/extension-demo1.fxml"))));
        JavaFxUtils.centerOnScreen(primaryStage);
        primaryStage.setTitle("Extension Demo 1");
        primaryStage.show();
    }

    public static void main (String[] args)
    {
        launch(args);
    }
}
