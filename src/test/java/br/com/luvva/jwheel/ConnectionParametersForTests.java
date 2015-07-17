package br.com.luvva.jwheel;

import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.model.beans.ConnectionParametersFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
@Specializes
public class ConnectionParametersForTests extends ConnectionParametersFactory
{
    @Produces
    @Specializes
    protected ConnectionParameters produce ()
    {
        return super.produce();
    }

    @Override
    public void setDefaultParameters (ConnectionParameters parameters)
    {
        parameters.setDatabaseDriver("org.postgresql.Driver");
        parameters.setDatabasePassword("postgres");
        parameters.setDatabaseUrl("jdbc:postgresql://localhost:5432/jwheel");
        parameters.setDatabaseUser("amsterdam");
    }
}
