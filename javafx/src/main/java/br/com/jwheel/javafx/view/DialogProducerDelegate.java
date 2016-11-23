package br.com.jwheel.javafx.view;

import br.com.jwheel.core.model.DecisionDialogModel;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DialogProducerDelegate
{
    private DialogProducerDelegate ()
    {
    }

    public static void showErrorAlert (String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessAlert (String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showDecisionDialog (DecisionDialogModel ddm)
    {
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(ddm.getDefaultOption(), ddm.getExtraOptions());
        choiceDialog.setContentText(ddm.getDecisionDescription());
        choiceDialog.showAndWait();
        ddm.setChosenOption(choiceDialog.getSelectedItem());
    }
}
