package br.com.jwheel.jpa;

import br.com.jwheel.xml.service.PreferencesFactoryFromXml;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class ConnectionParametersFactory extends PreferencesFactoryFromXml<ConnectionParameters>
{
    private @Inject ConnectionParametersDao xStreamDao;

    @Produces
    protected ConnectionParameters produce ()
    {
        return produce(xStreamDao);
    }

    @Override
    public void setDefaultPreferences (ConnectionParameters parameters)
    {
    }
}
