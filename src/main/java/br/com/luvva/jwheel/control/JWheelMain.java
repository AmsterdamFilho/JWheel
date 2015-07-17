package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.LogParameters;
import ch.qos.logback.classic.Level;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Vetoed
public class JWheelMain
{

    protected @Inject JwLoggerFactory loggerFactory;
    protected @Inject LogParameters   logParameters;

    public static void main (String[] args)
    {
        WeldContext.getInstance().getBean(JWheelMain.class);
    }

    @PostConstruct
    private void init ()
    {
        setLogger();
        if (!testDatabaseConnection())
        {
        }
        customInit();
    }

    protected void setConnection ()
    {
        Level loggerLevel = logParameters.getLoggerLevel();
        if (loggerLevel == null)
        {
            loggerFactory.configureLogbackAsDefault(logParameters.getLogFilePath());
        }
        else
        {
            loggerFactory.configureLogbackAsDefault(logParameters.getLogFilePath(), loggerLevel);
        }
        if (logParameters.isUseLoggerConfigurationXml())
        {
            loggerFactory.configureLogback(logParameters.getLoggerConfigurationXml());
        }
    }

    protected void setLogger ()
    {
        Level loggerLevel = logParameters.getLoggerLevel();
        if (loggerLevel == null)
        {
            loggerFactory.configureLogbackAsDefault(logParameters.getLogFilePath());
        }
        else
        {
            loggerFactory.configureLogbackAsDefault(logParameters.getLogFilePath(), loggerLevel);
        }
        if (logParameters.isUseLoggerConfigurationXml())
        {
            loggerFactory.configureLogback(logParameters.getLoggerConfigurationXml());
        }
    }

    protected boolean testDatabaseConnection ()
    {
        EntityManager em = WeldContext.getInstance().getBean(EntityManager.class);
        return true;
    }

    @SuppressWarnings ("EmptyMethod")
    protected void customInit ()
    {
    }

}
