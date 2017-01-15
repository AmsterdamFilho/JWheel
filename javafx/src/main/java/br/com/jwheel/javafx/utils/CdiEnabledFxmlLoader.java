package br.com.jwheel.javafx.utils;

import br.com.jwheel.core.service.cdi.WeldContext;
import javafx.fxml.FXMLLoader;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class CdiEnabledFxmlLoader extends FXMLLoader
{
    public CdiEnabledFxmlLoader ()
    {
        setControllerFactory(param -> WeldContext.getInstance().getBean(param));
    }
}
