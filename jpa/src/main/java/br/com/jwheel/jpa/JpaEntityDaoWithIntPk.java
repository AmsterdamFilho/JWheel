package br.com.jwheel.jpa;

import javax.persistence.EntityManager;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JpaEntityDaoWithIntPk<T> extends JpaEntityDao<T, Integer>
{
    public JpaEntityDaoWithIntPk (EntityManager entityManager)
    {
        super(entityManager);
    }
}
