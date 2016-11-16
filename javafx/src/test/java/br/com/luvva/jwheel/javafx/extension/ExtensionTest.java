package br.com.luvva.jwheel.javafx.extension;

import br.com.luvva.jwheel.javafx.view.JavaFxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ExtensionTest extends Application
{
    @Override
    public void start (Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        primaryStage.setScene(new Scene(fxmlLoader.load(getClass().getResourceAsStream("/fxml/extension.fxml"))));
        JavaFxUtils.centerOnScreen(primaryStage);
        primaryStage.show();
    }

    public static void main (String[] args)
    {
        launch(args);
    }
}