package br.com.luvva.jwheel.dao.jpa;

import br.com.luvva.jwheel.dao.EntityDao;
import br.com.luvva.jwheel.java.utils.JavaLangUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JpaEntityDao<T, PK> implements EntityDao<T, PK>
{
    protected EntityManager entityManager;

    public JpaEntityDao (EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

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
        //noinspection unchecked
        return q.getResultList();
    }

}
