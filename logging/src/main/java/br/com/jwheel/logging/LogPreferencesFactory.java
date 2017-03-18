package br.com.jwheel.logging;

import br.com.jwheel.xml.model.FromXmlPreferences;
import br.com.jwheel.xml.service.PreferencesFactoryFromXml;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public final class LogPreferencesFactory implements PreferencesFactoryFromXml<LogPreferences>
{
    private @Inject LogPreferencesDao             xStreamDao;
    private @Inject DefaultLogPreferencesProvider defaultLogPreferencesProvider;

    @Produces
    @FromXmlPreferences
    public LogPreferences produce ()
    {
        return produce(xStreamDao);
    }

    @Override
    public LogPreferences produceDefault ()
    {
        return defaultLogPreferencesProvider.provide();
    }
}
