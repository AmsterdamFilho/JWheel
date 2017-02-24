package br.com.jwheel.jpa;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
@Singleton
public class MyConnectionParametersFactory extends ConnectionParametersFactory
{
    private @Inject ConnectionParametersDao dao;

    @Produces
    public ConnectionParameters produce ()
    {
        return produce(dao);
    }

    @Override
    public void setDefaultPreferences (ConnectionParameters parameters)
    {
        parameters.setDriver("org.postgresql.Driver");
        parameters.setPassword("jwheel");
        parameters.setUrl("jdbc:postgresql://localhost:5433/jwheel");
        parameters.setUser("jwheel");
    }
}
