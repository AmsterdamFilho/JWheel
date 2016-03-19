package br.com.luvva.jwheel.model.providers;

import br.com.luvva.jwheel.model.beans.DecisionDialogModel;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface TextProvider
{

    String yes ();

    String no ();

    String save ();

    String cancel ();

    String ok ();

    String success ();

    String attention ();

    String savedSuccessfully ();

    String failedToSave ();

    String exitSystemQuestion ();

    String lafErrorMessage ();

    String databaseConnectionTestMessage ();

    DecisionDialogModel connectionTestFailedDecision ();

}
