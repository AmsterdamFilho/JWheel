package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.view.interfaces.ViewStarter;
import ch.qos.logback.classic.Level;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Vetoed
public class JWheelMain
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
        ConnectionTestModel connectionTestModel = new ConnectionTestModel();
        ConnectionTestTimer connectionTestTimer = new ConnectionTestTimer(connectionTestModel);
        connectionTestTimer.start();
        ConnectionTestThread connectionTest = new ConnectionTestThread(connectionTestModel);
        connectionTest.start();
        while (connectionTestModel.getConnectionTestResult() == null)
        {
            try
            {
                // without this line, this method does not work!
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                logger.error("Could not sleep main thread while connection test is running!", e);
            }
            if (connectionTestModel.isTimerExpired() && !connectionTestModel.isProgressDialogWasShown())
            {
                viewStarter.showConnectionTestProgressDialog();
                connectionTestModel.setProgressDialogWasShown(true);
            }
        }
        if (connectionTestModel.isProgressDialogWasShown())
        {
            viewStarter.closeConnectionTestProgressDialog();
        }
        return connectionTestModel.getConnectionTestResult();
    }

    @SuppressWarnings ("EmptyMethod")
    protected void customInit ()
    {
    }

    private class ConnectionTestThread extends Thread
    {

        private ConnectionTestModel connectionTestModel;

        private ConnectionTestThread (ConnectionTestModel connectionTestModel)
        {
            setDaemon(true);
            this.connectionTestModel = connectionTestModel;
        }

        @Override
        public void run ()
        {
            try
            {
                WeldContext.getInstance().getBean(EntityManager.class);
                connectionTestModel.setTestSucceeded(true);
            }
            catch (Exception ex)
            {
                logger.error("Could not create an EntityManager!", ex);
                connectionTestModel.setTestSucceeded(false);
            }
        }

    }

    private class ConnectionTestTimer extends Timer
    {

        private ConnectionTestModel connectionTestModel;

        private ConnectionTestTimer (ConnectionTestModel connectionTestModel)
        {
            super(true);
            this.connectionTestModel = connectionTestModel;
        }

        private void start ()
        {
            schedule(new TimerTask()
            {
                @Override
                public void run ()
                {
                    connectionTestModel.setTimerExpired(true);
                    cancel();
                }
            }, 2000);
        }

    }

    private class ConnectionTestModel
    {
        private boolean timerExpired           = false;
        private Boolean testSucceeded          = null;
        private boolean progressDialogWasShown = false;

        private boolean isTimerExpired ()
        {
            return timerExpired;
        }

        private void setTimerExpired (boolean timerExpired)
        {
            this.timerExpired = timerExpired;
        }

        private Boolean getConnectionTestResult ()
        {
            return testSucceeded;
        }

        private void setTestSucceeded (Boolean testSucceeded)
        {
            this.testSucceeded = testSucceeded;
        }

        public Boolean getTestSucceeded ()
        {
            return testSucceeded;
        }

        public boolean isProgressDialogWasShown ()
        {
            return progressDialogWasShown;
        }

        public void setProgressDialogWasShown (boolean progressDialogWasShown)
        {
            this.progressDialogWasShown = progressDialogWasShown;
        }
    }

}
