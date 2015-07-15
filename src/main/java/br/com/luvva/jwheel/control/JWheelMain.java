package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.LocalParameters;
import ch.qos.logback.classic.Level;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Vetoed
public class JWheelMain
{

    private @Inject JwLoggerFactory loggerFactory;
    private @Inject LocalParameters localParameters;

    public static void main (String[] args)
    {
        WeldContext.getInstance().getBean(JWheelMain.class);
    }

    @PostConstruct
    protected void init ()
    {
        setLogger();
        customInit();
    }

    protected void setLogger ()
    {
        Level loggerLevel = localParameters.getLoggerLevel();
        if (loggerLevel == null)
        {
            loggerFactory.configureLogbackAsDefault(localParameters.getLogFilePath());
        }
        else
        {
            loggerFactory.configureLogbackAsDefault(localParameters.getLogFilePath(), loggerLevel);
        }
        if (localParameters.isUseLoggerConfigurationXml())
        {
            loggerFactory.configureLogback(localParameters.getLoggerConfigurationXml());
        }
    }

    @SuppressWarnings ("EmptyMethod")
    protected void customInit ()
    {
    }

}
