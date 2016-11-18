package br.com.luvva.jwheel.jpa;

import br.com.luvva.jwheel.core.cdi.Custom;
import br.com.luvva.jwheel.xml.model.PasswordProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Custom
public class ConnectionParameters
{
    private StringProperty driver   = new SimpleStringProperty();
    private StringProperty url      = new SimpleStringProperty();
    private StringProperty password = new PasswordProperty();
    private StringProperty user     = new SimpleStringProperty();

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

    public String getDriver ()
    {
        return driver.get();
    }

    public StringProperty driverProperty ()
    {
        return driver;
    }

    public void setDriver (String driver)
    {
        this.driver.set(driver);
    }

    public String getUrl ()
    {
        return url.get();
    }

    public StringProperty urlProperty ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url.set(url);
    }

    public String getPassword ()
    {
        return password.get();
    }

    public StringProperty passwordProperty ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password.set(password);
    }

    public String getUser ()
    {
        return user.get();
    }

    public StringProperty userProperty ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user.set(user);
    }
}
