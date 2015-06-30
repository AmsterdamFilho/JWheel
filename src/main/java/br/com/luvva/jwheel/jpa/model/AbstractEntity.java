package br.com.luvva.jwheel.jpa.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@MappedSuperclass
public abstract class AbstractEntity
{

    @Version
    private int version;

    public abstract Serializable getCodigo ();

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

        return Objects.equals(getCodigo(), ((AbstractEntity) o).getCodigo());

    }

    @Override
    public int hashCode ()
    {
        Object codigo = getCodigo();
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
        return getClass().getSimpleName() + "{pk=" + String.valueOf(getCodigo()) + "}";
    }

}
