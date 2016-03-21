package br.com.luvva.jwheel.model.starter;

import br.com.luvva.jwheel.WeldContext;

import javax.persistence.EntityManager;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
class ConnectionTestThread extends Thread
{

    private ConnectionTest connectionTest;

    ConnectionTestThread (ConnectionTest connectionTest)
    {
        setDaemon(true);
        this.connectionTest = connectionTest;
    }

    @Override
    public void run ()
    {
        try
        {
            WeldContext.getInstance().getBean(EntityManager.class);
            connectionTest.setStatus(ConnectionTestStatus.SUCCEEDED);
        }
        catch (Exception ex)
        {
            connectionTest.setStatus(ConnectionTestStatus.FAILED);
            connectionTest.setConnectionFailedException("Could not create an EntityManager!", ex);
        }
    }

}
