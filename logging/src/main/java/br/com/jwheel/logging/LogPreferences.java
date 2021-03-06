package br.com.jwheel.logging;

import ch.qos.logback.classic.Level;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LogPreferences
{
    private String  loggerLevel;
    private String  loggerConfigurationXml;
    private String  logFilePath;
    private boolean useLoggerConfigurationXml;

    public String getLoggerLevel ()
    {
        return loggerLevel;
    }

    public void setLoggerLevel (String loggerLevel)
    {
        this.loggerLevel = loggerLevel;
    }

    public void setLoggerLevel (Level loggerLevel)
    {
        if (loggerLevel == null)
        {
            this.loggerLevel = null;
        }
        else
        {
            this.loggerLevel = loggerLevel.levelStr;
        }
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

    public String getLogFilePath ()
    {
        return logFilePath;
    }

    public void setLogFilePath (String logFilePath)
    {
        this.logFilePath = logFilePath;
    }
}
