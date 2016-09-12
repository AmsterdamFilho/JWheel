package br.com.luvva.jwheel.javafx.service;

import br.com.luvva.jwheel.cdi.WeldContext;
import br.com.luvva.jwheel.javafx.control.JavaFxApplication;
import br.com.luvva.jwheel.jpa.ConnectionParametersDao;
import br.com.luvva.jwheel.jpa.EntityManagerProducer;
import br.com.luvva.jwheel.jpa.ConnectionParameters;
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
        boolean response = WeldContext.getInstance().getBean(JavaFxApplication.class).databaseConnectionOk();
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
