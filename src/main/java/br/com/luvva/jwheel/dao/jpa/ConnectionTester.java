package br.com.luvva.jwheel.dao.jpa;

import br.com.luvva.jwheel.WeldContext;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionTester
{
    private @Inject Logger logger;

    public boolean execute ()
    {
        try
        {
            WeldContext.getInstance().getBean(EntityManager.class);
            return true;
        }
        catch (Exception ex)
        {
            logger.error("Could not create an EntityManager!", ex);
            return false;
        }
    }
}
