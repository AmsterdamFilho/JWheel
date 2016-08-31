package br.com.luvva.jwheel.view.javafx.utils;

import br.com.luvva.jwheel.model.providers.TextProvider;
import javafx.scene.control.Alert;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AlertProducer
{
    private @Inject TextProvider textProvider;

    public void showConnectionTestSucceededMessage ()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(textProvider.success());
        alert.setContentText(textProvider.connectionParametersRecordTestSuccessful());
        alert.showAndWait();
    }

    public void showConnectionTestFailedMessage ()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(textProvider.warning());
        alert.setContentText(textProvider.connectionParametersRecordTestFailed());
        alert.showAndWait();
    }

    public void showSaveSucceededMessage ()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(textProvider.success());
        alert.setContentText(textProvider.savedSuccessfully());
        alert.showAndWait();
    }

    public void showSaveFailedMessage ()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(textProvider.error());
        alert.setContentText(textProvider.failedToSave());
        alert.showAndWait();
    }
}
