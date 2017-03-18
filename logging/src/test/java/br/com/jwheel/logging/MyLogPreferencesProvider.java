package br.com.jwheel.logging;

import ch.qos.logback.classic.Level;

import javax.enterprise.inject.Specializes;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
public class MyLogPreferencesProvider extends DefaultLogPreferencesProvider
{
    @Override
    public LogPreferences provide ()
    {
        LogPreferences response = super.provide();
        response.setLoggerLevel(Level.ALL);
        return response;
    }
}
