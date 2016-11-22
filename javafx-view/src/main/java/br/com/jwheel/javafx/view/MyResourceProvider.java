package br.com.jwheel.javafx.view;

import br.com.jwheel.core.model.DecisionDialogModel;
import br.com.jwheel.core.model.ResourceProvider;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class MyResourceProvider extends ResourceProvider
{
    @Override
    protected String root ()
    {
        return "jwheel-javafx-view";
    }

    public void showErrorAlert (String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(getI18nProperty("z_alert_errorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showSuccessAlert (String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(getI18nProperty("z_alert_successTitle"));
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

    public String getInvalidatedControlCss ()
    {
        return getCss("invalidated");
    }

    public String getAutoCompleterCss ()
    {
        return getCss("auto-completer");
    }
}
