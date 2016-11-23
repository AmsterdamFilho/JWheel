package br.com.jwheel.control;

import br.com.jwheel.jpa.ConnectionParameters;
import br.com.jwheel.jpa.ConnectionParametersDao;
import br.com.jwheel.xml.service.PreferencesFactoryFromXml;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionParametersFactory extends PreferencesFactoryFromXml<ConnectionParameters>
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
