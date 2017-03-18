package br.com.jwheel.javafx;

import br.com.jwheel.weld.WeldContext;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JWheelFxmlLoader extends FXMLLoader
{
    public JWheelFxmlLoader (URL location)
    {
        super(location);
    }

    public JWheelFxmlLoader (URL location, ResourceBundle resources)
    {
        super(location, resources);
    }

    public JWheelFxmlLoader withCdi ()
    {
        setControllerFactory(param -> WeldContext.getInstance().getAny(param));
        return this;
    }

    public static <T> T loadWithCdi (URL location) throws IOException
    {
        return new JWheelFxmlLoader(location).withCdi().load();
    }

    public static <T> T loadWithCdi (URL location, ResourceBundle resourceBundle) throws IOException
    {
        return new JWheelFxmlLoader(location, resourceBundle).withCdi().load();
    }
}
