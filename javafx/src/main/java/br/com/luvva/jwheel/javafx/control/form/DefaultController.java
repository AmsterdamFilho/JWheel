package br.com.luvva.jwheel.javafx.control.form;

import br.com.luvva.jwheel.cdi.WeldContext;
import br.com.luvva.jwheel.javafx.control.JavaFxApplication;
import br.com.luvva.jwheel.javafx.view.controls.JwContentPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class DefaultController implements Initializable
{
    private @FXML JwContentPane  jwContentPane;
    private       URL            location;
    private       ResourceBundle resources;

    protected JwContentPane getJwContentPane ()
    {
        return jwContentPane;
    }

    protected URL getLocation ()
    {
        return location;
    }

    protected ResourceBundle getResources ()
    {
        return resources;
    }

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        this.location = location;
        this.resources = resources;
        jwContentPane.getStylesheets()
                     .addAll(WeldContext.getInstance().getBean(JavaFxApplication.class).getStylesheets());
        start();
    }

    protected void start ()
    {
    }
}
