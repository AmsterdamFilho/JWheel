package br.com.jwheel.jpa;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@MappedSuperclass
public abstract class AbstractEntity
{
    public abstract Serializable getId ();

    @Override
    public boolean equals (Object o)
    {
        if (this == o)
        {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        return Objects.equals(getId(), ((AbstractEntity) o).getId());

    }

    @Override
    public int hashCode ()
    {
        Object id = getId();
        if (id == null)
        {
            return 0;
        }
        if (Integer.class.equals(id.getClass()))
        {
            return ((Integer) id);
        }
        if (Long.class.equals(id.getClass()))
        {
            Long idAsLong = (Long) id;
            return (int) (idAsLong ^ (idAsLong >>> 32));
        }
        return id.hashCode();
    }

    @Override
    public String toString ()
    {
        return getClass().getSimpleName() + "{pk=" + String.valueOf(getId()) + "}";
    }
}
