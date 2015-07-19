package br.com.luvva.jwheel.dao.jpa;

import br.com.luvva.jwheel.dao.GenericDao;
import br.com.luvva.jwheel.java.utils.JavaLangUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JpaGenericDao<T, PK> implements GenericDao<T, PK>
{
    protected @Inject EntityManager entityManager;

    public void persist (T entity)
    {
        entityManager.persist(entity);
    }

    public void merge (T entity)
    {
        entityManager.merge(entity);
    }

    public void remove (T entity)
    {
        entityManager.remove(entity);
    }

    public void removeById (PK id)
    {
        T entity = getById(id);
        entityManager.remove(entity);
    }

    public T getById (PK id)
    {
        return entityManager.find(JavaLangUtils.getTypeArgumentClass(getClass()), id);
    }

    public Collection<T> findAll ()
    {
        Query q = entityManager.createQuery("from " + JavaLangUtils.getTypeArgumentClass(getClass()).getName());
        return q.getResultList();
    }

}
