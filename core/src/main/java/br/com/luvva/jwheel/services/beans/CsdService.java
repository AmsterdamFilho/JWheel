package br.com.luvva.jwheel.services.beans;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.control.JwApplication;
import br.com.luvva.jwheel.dao.jpa.EntityManagerProducer;
import br.com.luvva.jwheel.dao.xstream.ConnectionParametersDao;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class CsdService
{
    private @Inject EntityManagerProducer   entityManagerProducer;
    private @Inject ConnectionParametersDao dao;
    private @Inject Logger                  logger;

    public boolean testConnection (ConnectionParameters connectionParameters)
    {
        entityManagerProducer.init(connectionParameters);
        boolean response = WeldContext.getInstance().getBean(JwApplication.class).databaseConnectionOk();
        entityManagerProducer.init();
        return response;
    }

    public boolean saveOk (ConnectionParameters connectionParameters)
    {
        try
        {
            dao.merge(connectionParameters);
            entityManagerProducer.init();
            return true;
        }
        catch (Exception e)
        {
            logger.error("Could not persist Connection Parameters!", e);
            return false;
        }
    }
}
