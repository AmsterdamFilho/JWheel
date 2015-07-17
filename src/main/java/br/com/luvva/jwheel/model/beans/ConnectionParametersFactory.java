package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.dao.xstream.ConnectionParametersDao;
import org.slf4j.Logger;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class ConnectionParametersFactory implements ParameterFactory<ConnectionParameters>
{

    private @Inject ConnectionParametersDao   xStreamDao;
    private @Inject Logger                    logger;
    private @Inject XStreamParametersProducer producer;

    @Produces
    protected ConnectionParameters produce ()
    {
        return producer.produce(xStreamDao, logger, this);
    }

    @Override
    public void setDefaultParameters (ConnectionParameters parameters)
    {
        parameters.setDatabaseDriver("org.postgresql.Driver");
        parameters.setDatabasePassword("postgres");
        parameters.setDatabaseUrl("jdbc:postgresql://localhost:5433/postgres");
        parameters.setDatabaseUser("postgres");
    }

}
