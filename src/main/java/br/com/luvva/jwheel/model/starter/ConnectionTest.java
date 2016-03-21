package br.com.luvva.jwheel.model.starter;

import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionTest
{

    private ConnectionTestStatus status = ConnectionTestStatus.NOT_INITIATED;

    private ConnectionTestModel model;
    private Exception           connectionFailedException;

    private @Inject Logger logger;

    public synchronized boolean execute (ConnectionTestModel model)
    {
        if (status != ConnectionTestStatus.NOT_INITIATED)
        {
            throw new IllegalStateException("The test can only be executed once!");
        }
        this.model = model;
        status = ConnectionTestStatus.RUNNING;
        ConnectionTestThread connectionTestThread = new ConnectionTestThread(this);
        connectionTestThread.start();
        new ConnectionTestTimer(this, model).start();
        while (!statusIsSucceededOrFailed())
        {
            try
            {
                wait();
            }
            catch (InterruptedException ignored)
            {
            }
        }
        return ConnectionTestStatus.SUCCEEDED.equals(status);
    }

    public Exception getConnectionFailedException ()
    {
        return connectionFailedException;
    }

    void setConnectionFailedException (String description, Exception connectionFailedException)
    {
        this.connectionFailedException = connectionFailedException;
        logger.error(description, connectionFailedException);
    }

    private boolean statusIsSucceededOrFailed ()
    {
        return ConnectionTestStatus.FAILED.equals(status) || ConnectionTestStatus.SUCCEEDED.equals(status);
    }

    ConnectionTestStatus getStatus ()
    {
        return status;
    }

    synchronized void setStatus (ConnectionTestStatus status)
    {
        if (status.equals(ConnectionTestStatus.RUNNING_TAKING_TOO_LONG))
        {
            if (!this.status.equals(ConnectionTestStatus.RUNNING))
            {
                return;
            }
        }
        this.status = status;
        model.statusChanged(status);
        if (statusIsSucceededOrFailed())
        {
            notify();
        }
    }
}
