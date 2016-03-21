package br.com.luvva.jwheel.dao.jpa;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.model.jpa.PersistenceUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;
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
        init(WeldContext.getInstance().getBean(ConnectionParameters.class, new AnnotationLiteral<Default>() {}));
    }

    public void init (ConnectionParameters connectionParameters)
    {
        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("javax.persistence.jdbc.driver", connectionParameters.getDatabaseDriver());
        propertiesMap.put("javax.persistence.jdbc.url", connectionParameters.getDatabaseUrl());
        propertiesMap.put("javax.persistence.jdbc.user", connectionParameters.getDatabaseUser());
        propertiesMap.put("javax.persistence.jdbc.password", connectionParameters.getDatabasePassword());
        PersistenceUnit persistenceUnit = WeldContext.getInstance().getBean(PersistenceUnit.class);
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
