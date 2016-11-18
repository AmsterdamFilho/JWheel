package br.com.luvva.jwheel.javafx.control;

import br.com.luvva.jwheel.javafx.view.MyResourceProvider;
import br.com.luvva.jwheel.model.DecisionDialogModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class AlertProducer
{
    private @Inject MyResourceProvider myViewProvider;

    public void showErrorAlert (String message)
    {
        myViewProvider.showErrorAlert(message);
    }

    public void showSuccessAlert (String message)
    {
        myViewProvider.showSuccessAlert(message);
    }

    public void showDecisionDialog (DecisionDialogModel ddm)
    {
        myViewProvider.showDecisionDialog(ddm);
    }
}
