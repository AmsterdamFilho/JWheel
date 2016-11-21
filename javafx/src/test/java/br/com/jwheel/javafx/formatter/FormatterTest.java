package br.com.jwheel.javafx.formatter;

import br.com.jwheel.javafx.fxml.CdiEnabledFxmlLoader;
import br.com.jwheel.javafx.utils.JavaFxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FormatterTest extends Application
{
    @Override
    public void start (Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new CdiEnabledFxmlLoader();
        primaryStage.setScene(new Scene(fxmlLoader.load(getClass().getResourceAsStream("/fxml/formatter.fxml"))));
        JavaFxUtils.centerOnScreen(primaryStage);
        primaryStage.show();
    }

    public static void main (String[] args)
    {
        launch(args);
    }
}
