package br.com.luvva.jwheel.view.swing.utils;

import org.slf4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class InvokeAndWaitHandler
{

    private @Inject Logger logger;

    public void invokeAndLogOnError (Runnable runnable)
    {
        try
        {
            java.awt.EventQueue.invokeAndWait(runnable);
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            logger.error("Unexpected error while running invokeAndWait!", e);
        }
    }
}
