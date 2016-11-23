package br.com.jwheel.javafx;

import br.com.jwheel.core.model.DecisionDialogModel;
import br.com.jwheel.javafx.view.DialogProducerDelegate;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class DialogProducer
{
    private @Inject MyResourceProvider resourceProvider;

    public void showErrorAlert (String message)
    {
        DialogProducerDelegate.showErrorAlert(resourceProvider.getI18nProperty("z_alert_errorTitle"), message);
    }

    public void showSuccessAlert (String message)
    {
        DialogProducerDelegate.showSuccessAlert(resourceProvider.getI18nProperty("z_alert_successTitle"), message);
    }

    public void showDecisionDialog (DecisionDialogModel ddm)
    {
        DialogProducerDelegate.showDecisionDialog(ddm);
    }
}
