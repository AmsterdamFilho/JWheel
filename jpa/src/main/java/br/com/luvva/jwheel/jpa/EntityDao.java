package br.com.luvva.jwheel.jpa;

import java.util.Collection;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface EntityDao<T, PK>
{
    void persist (T entity);

    void merge (T entity);

    void remove (T entity);

    void removeById (PK id);

    T getById (PK id);

    Collection<T> findAll ();
}
