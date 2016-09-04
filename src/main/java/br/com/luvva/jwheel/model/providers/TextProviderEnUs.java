package br.com.luvva.jwheel.model.providers;

import br.com.luvva.jwheel.cdi.utils.Custom;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Custom
public class TextProviderEnUs implements TextProvider
{

    @Override
    public String yes ()
    {
        return "Yes";
    }

    @Override
    public String no ()
    {
        return "No";
    }

    @Override
    public String cancel ()
    {
        return "Cancel";
    }

    @Override
    public String save ()
    {
        return "Save";
    }

    @Override
    public String ok ()
    {
        return "OK";
    }

    @Override
    public String confirm ()
    {
        return "Confirm";
    }

    @Override
    public String success ()
    {
        return "Success";
    }

    @Override
    public String error ()
    {
        return "Error";
    }

    @Override
    public String info ()
    {
        return "Information";
    }

    @Override
    public String warning ()
    {
        return "Warning";
    }

    @Override
    public String savedSuccessfully ()
    {
        return "Saved successfully!";
    }

    @Override
    public String failedToSave ()
    {
        return "Error when saving";
    }

    @Override
    public String exitSystemQuestion ()
    {
        return "Do you wish to exit?";
    }

    @Override
    public String databaseConnectionTestMessage ()
    {
        return "Trying to connect to the database...";
    }

    @Override
    public DecisionDialogModel connectionTestFailedDecision ()
    {
        return new DecisionDialogModel(
                "Could not connect to the database! Maybe the server is down or the connection data is incorrect!",
                "Try again", "Configure connection", "Exit");
    }

    //<editor-fold desc="ConnectionParametersRecord">
    @Override
    public String connectionParametersRecordTitle ()
    {
        return "Database connection data";
    }

    @Override
    public String connectionParametersRecordURL ()
    {
        return "URL";
    }

    @Override
    public String connectionParametersRecordUser ()
    {
        return "User";
    }

    @Override
    public String connectionParametersRecordDriver ()
    {
        return "Driver";
    }

    @Override
    public String connectionParametersRecordTest ()
    {
        return "Test connection";
    }

    @Override
    public String connectionParametersRecordPassword ()
    {
        return "Password";
    }

    @Override
    public String connectionParametersRecordTestSuccessful ()
    {
        return "Connection succeeded!";
    }

    @Override
    public String connectionParametersRecordTestFailed ()
    {
        return "Could not connect to the database!";
    }
    //</editor-fold>
}
