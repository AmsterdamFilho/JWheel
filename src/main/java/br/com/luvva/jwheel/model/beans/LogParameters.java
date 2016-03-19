package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.cdi.utils.NewInstance;
import ch.qos.logback.classic.Level;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@NewInstance
public class LogParameters
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