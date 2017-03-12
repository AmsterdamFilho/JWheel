package br.com.jwheel.javafx;

import javax.enterprise.inject.Specializes;
import java.net.URL;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
public class MyTestResourceProvider extends JwJavaFxResourceProvider
{
    public URL getExtensionDemo1Fxml ()
    {
        return getFxml("extension-demo1");
    }

    public URL getExtensionDemo2Fxml ()
    {
        return getFxml("extension-demo2");
    }
}
