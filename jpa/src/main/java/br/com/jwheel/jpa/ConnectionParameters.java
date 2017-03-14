package br.com.jwheel.jpa;

import br.com.jwheel.xml.model.PasswordProperty;
import br.com.jwheel.cdi.Custom;
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
    private final StringProperty driver   = new SimpleStringProperty();
    private final StringProperty url      = new SimpleStringProperty();
    private final StringProperty password = new PasswordProperty();
    private final StringProperty user     = new SimpleStringProperty();

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

    public String getDatabase ()
    {
        String url = getUrl();
        if (url == null || !url.matches(".*/.+"))
        {
            return "";
        }
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public String getPort ()
    {
        String url = getUrl();
        if (url == null || !url.matches(".*:[0-9]+/[^/:]+"))
        {
            return "";
        }
        return url.substring(url.lastIndexOf(":") + 1, url.lastIndexOf("/"));
    }
}
