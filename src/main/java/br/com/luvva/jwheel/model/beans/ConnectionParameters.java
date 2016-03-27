package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.cdi.utils.NewInstance;
import br.com.luvva.jwheel.model.utils.SimpleEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<String> getDriversSuggestions ()
    {
        List<String> driversSuggestions = new ArrayList<>();
        driversSuggestions.add("org.postgresql.Driver");
        driversSuggestions.add("org.hsqldb.jdbcDriver");
        driversSuggestions.add("com.mysql.jdbc.Driver");
        driversSuggestions.add("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        driversSuggestions.add("oracle.jdbc.OracleDriver");
        return Collections.unmodifiableList(driversSuggestions);
    }

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
        return SimpleEncoder.decode(databasePassword);
    }

    public void setDatabasePassword (String databasePassword)
    {
        this.databasePassword = SimpleEncoder.encode(databasePassword);
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
