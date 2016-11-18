package br.com.luvva.jwheel.jpa;

import br.com.luvva.jwheel.core.cdi.WeldContext;

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
        propertiesMap.put("javax.persistence.jdbc.driver", connectionParameters.getDriver());
        propertiesMap.put("javax.persistence.jdbc.url", connectionParameters.getUrl());
        propertiesMap.put("javax.persistence.jdbc.user", connectionParameters.getUser());
        propertiesMap.put("javax.persistence.jdbc.password", connectionParameters.getPassword());
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
