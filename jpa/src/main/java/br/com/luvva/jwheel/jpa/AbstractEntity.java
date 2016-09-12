package br.com.luvva.jwheel.jpa;

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
        Object codigo = getId();
        if (codigo == null)
        {
            return 0;
        }
        if (Integer.class.equals(codigo.getClass()))
        {
            return ((Integer) codigo);
        }
        if (Long.class.equals(codigo.getClass()))
        {
            Long codigoLong = (Long) codigo;
            return (int) (codigoLong ^ (codigoLong >>> 32));
        }
        return codigo.hashCode();
    }

    @Override
    public String toString ()
    {
        return getClass().getSimpleName() + "{pk=" + String.valueOf(getId()) + "}";
    }
}
