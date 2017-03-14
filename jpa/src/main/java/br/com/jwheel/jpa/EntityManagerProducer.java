package br.com.jwheel.jpa;

import br.com.jwheel.cdi.WeldContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
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
    private EntityManagerFactory emf;

    @PostConstruct
    public void init ()
    {
        init(WeldContext.getInstance().getDefault(ConnectionParameters.class));
    }

    public void init (ConnectionParameters connectionParameters)
    {
        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("javax.persistence.jdbc.driver", connectionParameters.getDriver());
        propertiesMap.put("javax.persistence.jdbc.url", connectionParameters.getUrl());
        propertiesMap.put("javax.persistence.jdbc.user", connectionParameters.getUser());
        propertiesMap.put("javax.persistence.jdbc.password", connectionParameters.getPassword());
        PersistenceUnit persistenceUnit = WeldContext.getInstance().getAny(PersistenceUnit.class);
        emf = Persistence.createEntityManagerFactory(persistenceUnit.getName(), propertiesMap);
    }

    @Produces
    private EntityManager getEntityManager ()
    {
        return emf.createEntityManager();
    }

    private void close (@Disposes EntityManager entityManager)
    {
        if (entityManager.isOpen())
        {
            entityManager.close();
        }
    }

    @PreDestroy
    private void preDestroy ()
    {
        if (emf != null)
        {
            emf.close();
        }
    }
}
