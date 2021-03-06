package br.com.jwheel.javafx.extension;

import br.com.jwheel.weld.WeldContext;
import br.com.jwheel.javafx.TestResourceProvider;

import java.net.URL;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ExtensionDemo1 extends ExtensionDemo
{
    @Override
    String title ()
    {
        return "Extension demo 1";
    }

    @Override
    URL getFxml ()
    {
        return WeldContext.getInstance().getAny(TestResourceProvider.class).getExtensionDemo1Fxml();
    }

    public static void main (String[] args)
    {
        launch(args);
    }
}
