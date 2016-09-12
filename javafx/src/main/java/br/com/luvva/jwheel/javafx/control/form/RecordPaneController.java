package br.com.luvva.jwheel.javafx.control.form;

import br.com.luvva.jwheel.cdi.WeldContext;
import br.com.luvva.jwheel.javafx.control.JavaFxApplication;
import br.com.luvva.jwheel.javafx.view.utils.EnterPressedEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class RecordPaneController implements Initializable
{
    private @FXML Pane           contentPane;
    private       URL            location;
    private       ResourceBundle resources;

    private EnterPressedEventHandler enterPressedEventHandler = new EnterPressedEventHandler();

    protected EnterPressedEventHandler getEnterPressedEventHandler ()
    {
        return enterPressedEventHandler;
    }

    protected Pane getContentPane ()
    {
        return contentPane;
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
        contentPane.getStylesheets()
                   .add(WeldContext.getInstance().getBean(JavaFxApplication.class).getRecordPaneStyleSheet());
        contentPane.addEventFilter(KeyEvent.KEY_PRESSED, enterPressedEventHandler);
        start();
    }

    protected void start ()
    {
    }
}
