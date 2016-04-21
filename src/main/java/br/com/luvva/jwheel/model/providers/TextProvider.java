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

    String confirm ();

    String success ();

    String attention ();

    String savedSuccessfully ();

    String failedToSave ();

    String exitSystemQuestion ();

    String databaseConnectionTestMessage ();

    DecisionDialogModel connectionTestFailedDecision ();

    //<editor-fold desc="ConnectionParametersRecord">
    String connectionParametersRecordTitle ();

    String connectionParametersRecordURL ();

    String connectionParametersRecordUser ();

    String connectionParametersRecordDriver ();

    String connectionParametersRecordTest ();

    String connectionParametersRecordPassword ();

    String connectionParametersRecordTestSuccessful ();

    String connectionParametersRecordTestFailed ();
    //</editor-fold>
}
