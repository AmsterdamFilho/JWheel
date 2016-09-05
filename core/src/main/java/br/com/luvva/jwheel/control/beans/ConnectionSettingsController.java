package br.com.luvva.jwheel.control.beans;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.dao.jpa.EntityManagerProducer;
import br.com.luvva.jwheel.dao.xstream.ConnectionParametersDao;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.view.javafx.starter.FxConnectionSettings;
import br.com.luvva.jwheel.view.javafx.utils.AlertProducer;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionSettingsController
{
    private @Inject ConnectionParameters    connectionParameters;
    private @Inject EntityManagerProducer   entityManagerProducer;
    private @Inject ConnectionParametersDao dao;
    private @Inject Logger                  logger;
    private @Inject AlertProducer           alertProducer;

    private FxConnectionSettings view;

    public ConnectionParameters getCurrentConnectionParameters ()
    {
        return connectionParameters;
    }

    public void persistConnectionParameters ()
    {
        try
        {
            dao.merge(connectionParameters);
            alertProducer.showSaveSucceededMessage();
            view.close();
        }
        catch (Exception e)
        {
            alertProducer.showSaveFailedMessage();
            logger.error("Could not persist Connection Parameters", e);
        }
        entityManagerProducer.init();
    }

    public void testConnectionParameters ()
    {
        entityManagerProducer.init(connectionParameters);
        if (WeldContext.getInstance().getBean(ConnectionTester.class).execute())
        {
            alertProducer.showConnectionTestSucceededMessage();
        }
        else
        {
            alertProducer.showConnectionTestFailedMessage();
        }
        entityManagerProducer.init();
    }

    public void setView (FxConnectionSettings view)
    {
        this.view = view;
    }
}
