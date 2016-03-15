package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.view.interfaces.ViewConfigurator;
import ch.qos.logback.classic.Level;
import org.slf4j.Logger;

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

    private @Inject JwLoggerFactory  loggerFactory;
    private @Inject LogParameters    logParameters;
    private @Inject Logger           logger;
    private @Inject ViewConfigurator viewConfigurator;

    public static void main (String[] args)
    {
        WeldContext.getInstance().getBean(JWheelMain.class);
    }

    @PostConstruct
    private void init ()
    {
        setLogger();
        viewConfigurator.configureView();
        if (!databaseConnectionOk())
        {
        }
        customInit();
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

    protected boolean databaseConnectionOk ()
    {
        try
        {
            WeldContext.getInstance().getBean(EntityManager.class);
            return true;
        }
        catch (Exception ex)
        {
            logger.error("Could not create an EntityManager!", ex);
            return false;
        }
    }

    @SuppressWarnings ("EmptyMethod")
    protected void customInit ()
    {
    }

}
