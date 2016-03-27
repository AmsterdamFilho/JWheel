package br.com.luvva.jwheel.view.interfaces;

import br.com.luvva.jwheel.model.beans.DecisionDialogModel;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface ViewStarter
{
    void configureView ();

    void showConnectionTestProgressDialog ();

    void closeConnectionTestProgressDialog ();

    void showConnectionTestFailedDialogDecision (DecisionDialogModel ddm);

    void showConnectionSettingsDialog ();
}
