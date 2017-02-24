package br.com.jwheel.javafx.laf;

import br.com.jwheel.core.model.view.DecisionDialogModel;
import br.com.jwheel.javafx.JwJavaFxResourceProvider;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class DialogProducer
{
    private @Inject JwJavaFxResourceProvider resourceProvider;

    public void showErrorAlert (String message)
    {
        showAlert(Alert.AlertType.ERROR, resourceProvider.getI18nProperty("z_alert_errorTitle"), message);
    }

    public void showSuccessAlert (String message)
    {
        showAlert(Alert.AlertType.INFORMATION, resourceProvider.getI18nProperty("z_alert_successTitle"), message);
    }

    public void showDecisionDialog (DecisionDialogModel ddm)
    {
        // TODO: 22/02/17 change the UI. Remove cancel and confirm buttons, leaving just the options list.
        // If the user closes the dialog without choosing an option, default option is the chosen option
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(ddm.getDefaultOption(), ddm.getOtherOptions());
        choiceDialog.setContentText(ddm.getDecisionDescription());
        choiceDialog.showAndWait();
        ddm.setChosenOption(choiceDialog.getSelectedItem());
    }

    protected void showAlert (Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
