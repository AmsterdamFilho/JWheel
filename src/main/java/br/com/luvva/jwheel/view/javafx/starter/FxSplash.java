package br.com.luvva.jwheel.view.javafx.starter;

import br.com.luvva.jwheel.control.JwApplication;
import javafx.scene.Scene;

/**
 * <p>Splash screen generator. It must not rely upon any JavaFX configuration in {@link JwApplication#configureJavaFX()}
 * method.</p>
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface FxSplash
{
    Scene getScene ();
}
