package br.com.luvva.jwheel.model.starter;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.model.utils.LongTaskManager;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionTest implements Runnable
{

    private @Inject Logger  logger;
    private         Boolean result;

    public boolean execute ()
    {
        if (result != null)
        {
            throw new IllegalStateException("The test has ran already!");
        }
        run();
        return result;
    }

    public boolean execute (int acceptableDuration, Runnable acceptableDurationExpiredHandler)
    {
        if (result != null)
        {
            throw new IllegalStateException("The test has ran already!");
        }
        new LongTaskManager(this, acceptableDuration, acceptableDurationExpiredHandler).executeAndWait();
        return result;
    }

    @Override
    public void run ()
    {
        try
        {
            Thread.sleep(10000);
            WeldContext.getInstance().getBean(EntityManager.class);
            this.result = Boolean.TRUE;
        }
        catch (Exception ex)
        {
            logger.error("Could not create an EntityManager!", ex);
            this.result = Boolean.FALSE;
        }
    }
}
