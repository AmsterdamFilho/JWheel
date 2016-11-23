package br.com.luvva.jwheel.control;

import br.com.jwheel.template.control.JavaFxApplication;
import javafx.stage.Stage;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class JavaFxApplicationTest extends JavaFxApplication
{
    @Override
    public void init (Stage primaryStage)
    {
        primaryStage.close();
    }
}
