package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.cdi.utils.NewInstance;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@NewInstance
public class ConnectionParameters
{
    private String databaseDriver;
    private String databaseUrl;
    private String databasePassword;
    private String databaseUser;

    public String getDatabaseDriver ()
    {
        return databaseDriver;
    }

    public void setDatabaseDriver (String databaseDriver)
    {
        this.databaseDriver = databaseDriver;
    }

    public String getDatabaseUrl ()
    {
        return databaseUrl;
    }

    public void setDatabaseUrl (String databaseUrl)
    {
        this.databaseUrl = databaseUrl;
    }

    public String getDatabasePassword ()
    {
        return databasePassword;
    }

    public void setDatabasePassword (String databasePassword)
    {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseUser ()
    {
        return databaseUser;
    }

    public void setDatabaseUser (String databaseUser)
    {
        this.databaseUser = databaseUser;
    }
}
