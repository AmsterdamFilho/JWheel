package br.com.jwheel.javafx.utils;

import br.com.jwheel.core.service.cdi.WeldContext;
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
        setControllerFactory(param -> WeldContext.getInstance().getBean(param));
        return this;
    }

    public static <T> T loadWithCdi (URL location) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.setControllerFactory(param -> WeldContext.getInstance().getBean(param));
        return fxmlLoader.load();
    }

    public static <T> T loadWithCdi (URL location, ResourceBundle resourceBundle) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(location, resourceBundle);
        fxmlLoader.setControllerFactory(param -> WeldContext.getInstance().getBean(param));
        return fxmlLoader.load();
    }
}
