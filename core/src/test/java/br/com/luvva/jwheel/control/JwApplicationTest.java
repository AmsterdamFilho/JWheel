package br.com.luvva.jwheel.control;

import javafx.stage.Stage;

import javax.enterprise.inject.Specializes;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
@Singleton
public class JwApplicationTest extends JwApplication
{
    @Override
    public void init (Stage primaryStage)
    {
        primaryStage.close();
    }
}
