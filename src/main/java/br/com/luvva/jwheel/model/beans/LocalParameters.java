package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.cdi.utils.NewInstance;
import ch.qos.logback.classic.Level;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@NewInstance
public class LocalParameters
{
    private String databaseDriver;
    private String databaseUrl;
    private String databasePassword;
    private String databaseUser;

    private Level   loggerLevel;
    private String  loggerConfigurationXml;
    private boolean useLoggerConfigurationXml;

    private String appDataDirectory;
    private String logFilePath;

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

    public Level getLoggerLevel ()
    {
        return loggerLevel;
    }

    public void setLoggerLevel (Level loggerLevel)
    {
        this.loggerLevel = loggerLevel;
    }

    public String getLoggerConfigurationXml ()
    {
        return loggerConfigurationXml;
    }

    public void setLoggerConfigurationXml (String loggerConfigurationXml)
    {
        this.loggerConfigurationXml = loggerConfigurationXml;
    }

    public boolean isUseLoggerConfigurationXml ()
    {
        return useLoggerConfigurationXml;
    }

    public void setUseLoggerConfigurationXml (boolean useLoggerConfigurationXml)
    {
        this.useLoggerConfigurationXml = useLoggerConfigurationXml;
    }

    public String getAppDataDirectory ()
    {
        return appDataDirectory;
    }

    public void setAppDataDirectory (String appDataDirectory)
    {
        this.appDataDirectory = appDataDirectory;
    }

    public String getLogFilePath ()
    {
        return logFilePath;
    }

    public void setLogFilePath (String logFilePath)
    {
        this.logFilePath = logFilePath;
    }

}
