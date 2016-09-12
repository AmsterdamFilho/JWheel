package br.com.luvva.jwheel.logging;

import br.com.luvva.jwheel.cdi.Custom;
import ch.qos.logback.classic.Level;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Custom
class LogPreferences
{
    private Level   loggerLevel;
    private String  loggerConfigurationXml;
    private boolean useLoggerConfigurationXml;

    private String logFilePath;

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

    public String getLogFilePath ()
    {
        return logFilePath;
    }

    public void setLogFilePath (String logFilePath)
    {
        this.logFilePath = logFilePath;
    }
}
