package br.com.jwheel.template.control;

import br.com.jwheel.javafx.utils.EnterPressedEventHandler;
import br.com.jwheel.template.JwTemplateResourceProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class RecordPaneController implements Initializable
{
    private @FXML   Pane                       contentPane;
    private @Inject JwTemplateResourceProvider resourceProvider;

    private final EnterPressedEventHandler enterPressedEventHandler = new EnterPressedEventHandler();

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        contentPane.getStylesheets().add(resourceProvider.recordPaneCss());
        contentPane.addEventFilter(KeyEvent.KEY_PRESSED, enterPressedEventHandler);
        start();
    }

    protected void start ()
    {
    }
}
