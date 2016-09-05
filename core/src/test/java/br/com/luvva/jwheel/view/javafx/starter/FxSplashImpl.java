package br.com.luvva.jwheel.view.javafx.starter;

import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FxSplashImpl implements FxSplash
{
    @Override
    public Scene getScene ()
    {
        return new Scene(new StackPane(new ProgressIndicator(-1)), 100, 100);
    }
}
