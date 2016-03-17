package br.com.luvva.jwheel.model.providers;

import br.com.luvva.jwheel.model.beans.DecisionDialogModel;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface TextProvider
{

    String yes ();

    String no ();

    String exitSystemQuestion ();

    String lafErrorMessage ();

    String databaseConnectionTestMessage ();

    DecisionDialogModel connectionTestFailedDecision ();

}
