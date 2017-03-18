package br.com.jwheel.jpa.service;

import br.com.jwheel.jpa.model.ConnectionParameters;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AdminDatabaseParametersProvider
{
    protected ConnectionParameters provide ()
    {
        ConnectionParameters connectionParameters = new ConnectionParameters();
        connectionParameters.setDriver("org.postgresql.Driver");
        connectionParameters.setPassword("John1030");
        connectionParameters.setUrl("jdbc:postgresql://localhost:5432/postgres");
        connectionParameters.setUser("postgres");
        return connectionParameters;
    }
}
