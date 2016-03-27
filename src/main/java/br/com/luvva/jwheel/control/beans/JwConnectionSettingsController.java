package br.com.luvva.jwheel.control.beans;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.dao.jpa.EntityManagerProducer;
import br.com.luvva.jwheel.dao.xstream.ConnectionParametersDao;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.view.interfaces.ConnectionSettingsView;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwConnectionSettingsController implements ConnectionSettingsController
{

    private @Inject ConnectionParameters    connectionParameters;
    private @Inject EntityManagerProducer   entityManagerProducer;
    private @Inject ConnectionParametersDao dao;
    private @Inject Logger                  logger;

    private ConnectionSettingsView view;

    @Override
    public ConnectionParameters getCurrentConnectionParameters ()
    {
        return connectionParameters;
    }

    @Override
    public void persistConnectionParameters ()
    {
        try
        {
            dao.merge(connectionParameters);
            view.showSaveSucceededMessageAndClose();
        }
        catch (Exception e)
        {
            view.showSaveFailedMessage();
            logger.error("Could not persist Connection Parameters", e);
        }
        entityManagerProducer.init();
    }

    @Override
    public void testConnectionParameters ()
    {
        entityManagerProducer.init(connectionParameters);
        if (WeldContext.getInstance().getBean(ConnectionTester.class).execute())
        {
            view.showTestSucceededMessage();
        }
        else
        {
            view.showTestFailedMessage();
        }
        entityManagerProducer.init();
    }

    @Override
    public void setView (ConnectionSettingsView view)
    {
        this.view = view;
    }

}
