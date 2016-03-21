package br.com.luvva.jwheel.model.starter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
class ConnectionTestTimer extends Timer
{
    private ConnectionTest      connectionTest;
    private ConnectionTestModel connectionTestModel;

    public ConnectionTestTimer (ConnectionTest connectionTest, ConnectionTestModel connectionTestModel)
    {
        super(true);
        this.connectionTest = connectionTest;
        this.connectionTestModel = connectionTestModel;
    }

    void start ()
    {
        schedule(new TimerTask()
        {
            @Override
            public void run ()
            {
                connectionTest.setStatus(ConnectionTestStatus.RUNNING_TAKING_TOO_LONG);
                cancel();
            }
        }, connectionTestModel.timerDelay());
    }
}
