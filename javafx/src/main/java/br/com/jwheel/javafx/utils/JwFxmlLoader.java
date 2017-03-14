package br.com.jwheel.javafx.utils;

import br.com.jwheel.cdi.WeldContext;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwFxmlLoader extends FXMLLoader
{
    public JwFxmlLoader (URL location)
    {
        super(location);
    }

    public JwFxmlLoader (URL location, ResourceBundle resources)
    {
        super(location, resources);
    }

    public JwFxmlLoader withCdi ()
    {
        setControllerFactory(param -> WeldContext.getInstance().getAny(param));
        return this;
    }

    public static <T> T loadWithCdi (URL location) throws IOException
    {
        return new JwFxmlLoader(location).withCdi().load();
    }

    public static <T> T loadWithCdi (URL location, ResourceBundle resourceBundle) throws IOException
    {
        return new JwFxmlLoader(location, resourceBundle).withCdi().load();
    }
}
