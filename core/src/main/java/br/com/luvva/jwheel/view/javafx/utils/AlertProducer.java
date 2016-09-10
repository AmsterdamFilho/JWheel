package br.com.luvva.jwheel.view.javafx.utils;

import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.i18n.TextProvider;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class AlertProducer
{
    private @Inject Logger       logger;
    private @Inject TextProvider textProvider;

    public void showErrorAlert (String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(textProvider.getText(TextProvider.z_a_errorTitle));
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showSuccessAlert (String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(textProvider.getText(TextProvider.z_a_successTitle));
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showDecisionDialog (DecisionDialogModel ddm)
    {
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(ddm.getDefaultOption(), ddm.getExtraOptions());
        choiceDialog.setContentText(ddm.getDecisionDescription());
        choiceDialog.showAndWait();
        ddm.setChosenOption(choiceDialog.getSelectedItem());
    }
}
