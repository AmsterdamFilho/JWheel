package br.com.luvva.jwheel.javafx.view;

import br.com.luvva.jwheel.model.DecisionDialogModel;
import br.com.luvva.jwheel.provider.ResourceProvider;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class MyResourceProvider extends ResourceProvider
{
    private @Inject MyTextProvider myTextProvider;

    @Override
    protected String rootResourceDirectory ()
    {
        return "jwheel-javafx-view/laf/default/";
    }

    public void showErrorAlert (String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(myTextProvider.getText(MyTextProvider.z_a_errorTitle));
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showSuccessAlert (String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(myTextProvider.getText(MyTextProvider.z_a_successTitle));
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
