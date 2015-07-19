package br.com.luvva.jwheel.dao;

import java.util.Collection;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface GenericDao<T, PK>
{
    void persist (T entity);

    void merge (T entity);

    void remove (T entity);

    void removeById (PK id);

    T getById (PK id);

    Collection<T> findAll ();
}
