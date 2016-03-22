package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.model.utils.Wrapper;
import br.com.luvva.jwheel.model.utils.LongTaskManager;
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
                    viewStarter.showConnectionSettingsDialog();
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
        ConnectionTester connectionTester = WeldContext.getInstance().getBean(ConnectionTester.class);
        Wrapper<Boolean> wrapper = new Wrapper<>();
        LongTaskManager connectionTestManager =
                new LongTaskManager(() -> wrapper.setValue(connectionTester.execute()), 2000,
                        () -> viewStarter.showConnectionTestProgressDialog());
        connectionTestManager.executeAndWait();
        viewStarter.closeConnectionTestProgressDialog();
        return wrapper.getValue();
    }

    @SuppressWarnings ("EmptyMethod")
    protected void customInit ()
    {
    }

}
