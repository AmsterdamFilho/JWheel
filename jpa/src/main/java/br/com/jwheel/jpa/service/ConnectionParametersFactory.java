package br.com.jwheel.jpa.service;

import br.com.jwheel.jpa.dao.ConnectionParametersDao;
import br.com.jwheel.jpa.model.AdminDatabase;
import br.com.jwheel.jpa.model.ConnectionParameters;
import br.com.jwheel.jpa.model.ProductDatabase;
import br.com.jwheel.xml.service.PreferencesFactoryFromXml;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public final class ConnectionParametersFactory implements PreferencesFactoryFromXml<ConnectionParameters>
{
    private @Inject ConnectionParametersDao           dao;
    private @Inject AdminDatabaseParametersProvider   adminParametersProvider;
    private @Inject ProductDatabaseParametersProvider productParametersProvider;

    @Produces
    @AdminDatabase
    public ConnectionParameters produceAdminDatabaseParameters ()
    {
        return produce(dao);
    }

    @Produces
    @ProductDatabase
    public ConnectionParameters produceProductDatabaseParameters ()
    {
        return productParametersProvider.provide();
    }

    @Override
    public ConnectionParameters produceDefault ()
    {
        return adminParametersProvider.provide();
    }
}
