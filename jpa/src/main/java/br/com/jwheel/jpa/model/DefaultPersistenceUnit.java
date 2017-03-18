package br.com.jwheel.jpa.model;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DefaultPersistenceUnit implements PersistenceUnit
{
    @Override
    public String getName ()
    {
        return "default";
    }
}
