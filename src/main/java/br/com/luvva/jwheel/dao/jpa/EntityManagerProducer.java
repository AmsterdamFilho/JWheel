package br.com.luvva.jwheel.dao.jpa;

import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.model.jpa.PersistenceUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class EntityManagerProducer
{

    private @Inject ConnectionParameters connectionParameters;
    private @Inject PersistenceUnit      persistenceUnit;

    private EntityManagerFactory emf;

    @PostConstruct
    private void init ()
    {
        Map<String, String> settings = new HashMap<>();
        settings.put("javax.persistence.jdbc.driver", connectionParameters.getDatabaseDriver());
        settings.put("javax.persistence.jdbc.url", connectionParameters.getDatabaseUrl());
        settings.put("javax.persistence.jdbc.user", connectionParameters.getDatabaseUser());
        settings.put("javax.persistence.jdbc.password", connectionParameters.getDatabasePassword());
        emf = Persistence.createEntityManagerFactory(persistenceUnit.getName(), settings);
    }

    @Produces
    private EntityManager getEntityManager ()
    {
        return emf.createEntityManager();
    }

    public void close (@Disposes EntityManager entityManager)
    {
        if (entityManager.isOpen())
        {
            entityManager.close();
        }
    }
}
