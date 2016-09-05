package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;
import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.model.providers.TextProvider;
import javafx.stage.Stage;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwApplication
{
    private @Inject JwLoggerFactory  jwLoggerFactory;
    private @Inject Logger           logger;
    private @Inject TextProvider     textProvider;
    private @Inject LogParameters    logParameters;
    private @Inject ConnectionTester connectionTester;

    public void configureJavaFX ()
    {
    }

    public void configureLogger ()
    {
        jwLoggerFactory.configure(logParameters);
    }

    public boolean databaseConnectionOk ()
    {
        return connectionTester.execute();
    }

    public DecisionDialogModel getConnectionTestFailedDecisionModel ()
    {
        return textProvider.connectionTestFailedDecision();
    }

    public void init (Stage primaryStage)
    {
    }
}
