package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.model.starter.ConnectionTest;
import br.com.luvva.jwheel.model.starter.ConnectionTestModel;
import br.com.luvva.jwheel.model.starter.ConnectionTestStatus;
import br.com.luvva.jwheel.view.interfaces.ViewStarter;
import ch.qos.logback.classic.Level;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Vetoed
public class JWheelMain implements ConnectionTestModel
{

    private @Inject JwLoggerFactory loggerFactory;
    private @Inject LogParameters   logParameters;
    private @Inject Logger          logger;
    private @Inject ViewStarter     viewStarter;
    private @Inject TextProvider    textProvider;

    public static void main (String[] args)
    {
        WeldContext.getInstance().getBean(JWheelMain.class);
    }

    @PostConstruct
    private void init ()
    {
        setLogger();
        viewStarter.configureView();
        while (!databaseConnectionOk())
        {
            DecisionDialogModel ddm = textProvider.connectionTestFailedDecision();
            viewStarter.showConnectionTestFailedDialogDecision(ddm);
            switch (ddm.getChosenOption())
            {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    return;
                default:
                    assert false : "Connection test failed decision invalid!";
            }
        }
        customInit();
    }

    protected void setLogger ()
    {
        Level loggerLevel = logParameters.getLoggerLevel();
        if (logParameters.isUseLoggerConfigurationXml())
        {
            loggerFactory.configureLogback(logParameters.getLoggerConfigurationXml());
        }
        else
        {
            if (loggerLevel == null)
            {
                loggerFactory.configureLogbackAsDefault(logParameters.getLogFilePath());
            }
            else
            {
                loggerFactory.configureLogbackAsDefault(logParameters.getLogFilePath(), loggerLevel);
            }
        }
    }

    protected boolean databaseConnectionOk ()
    {
        return WeldContext.getInstance().getBean(ConnectionTest.class).execute(this);
    }

    @Override
    public int timerDelay ()
    {
        return 2000;
    }

    @Override
    public void statusChanged (ConnectionTestStatus newStatus)
    {
        switch (newStatus)
        {
            case RUNNING_TAKING_TOO_LONG:
                viewStarter.showConnectionTestProgressDialog();
                break;
            case FAILED:
            case SUCCEEDED:
                viewStarter.closeConnectionTestProgressDialog();
                break;
            default:
        }
    }

    @SuppressWarnings ("EmptyMethod")
    protected void customInit ()
    {
    }

}
