package br.com.luvva.jwheel.model;

/**
 * A ready to use Delegate that provides a T object
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface ProviderDelegate<T>
{
    T provide ();
}
