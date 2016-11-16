package br.com.luvva.jwheel.javafx.fxml;

import br.com.luvva.jwheel.cdi.WeldContext;
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
