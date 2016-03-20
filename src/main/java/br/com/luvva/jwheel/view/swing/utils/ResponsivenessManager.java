package br.com.luvva.jwheel.view.swing.utils;

import br.com.luvva.jwheel.model.utils.BooleanWrapper;

import javax.swing.*;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ResponsivenessManager implements ActionListener
{
    private Runnable  runnable;
    private Component component;

    private boolean responded = false;
    private Cursor originalCursor;

    private int waitCursor   = Cursor.WAIT_CURSOR;
    private int allowedDelay = 500;
    private boolean freezeUI;

    public ResponsivenessManager (Component component, Runnable runnable)
    {
        this(component, runnable, true);
    }

    public ResponsivenessManager (Component component, Runnable runnable, boolean freezeUI)
    {
        this.component = component;
        this.runnable = runnable;
        this.freezeUI = freezeUI;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        runAction();
    }

    public void runAction ()
    {
        originalCursor = component.getCursor();
        MySw mySw = new MySw();
        mySw.execute();
        if (freezeUI)
        {
            BooleanWrapper allowedDelayExpired = new BooleanWrapper();
            allowedDelayExpired.setValue(false);
            java.util.Timer timer = new java.util.Timer(true);
            timer.schedule(new TimerTask()
            {
                @Override
                public void run ()
                {
                    allowedDelayExpired.setValue(true);
                }
            }, allowedDelay);
            while (!mySw.isDone())
            {
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException ignored)
                {
                }
                if (allowedDelayExpired.isValue() && !responded)
                {
                    respond();
                }
            }
        }
        else
        {
            Timer timer = new Timer(allowedDelay, e -> {
                if (!mySw.isDone())
                {
                    respond();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void respond ()
    {
        component.setCursor(createWaitCursor());
        responded = true;
    }

    public void setWaitCursor (int waitCursor)
    {
        this.waitCursor = waitCursor;
    }

    public void setAllowedDelay (int allowedDelay)
    {
        this.allowedDelay = allowedDelay;
    }

    public Cursor createWaitCursor ()
    {
        return new Cursor(waitCursor);
    }

    private class MySw extends SwingWorker<Void, Void>
    {

        @Override
        protected Void doInBackground () throws Exception
        {
            runnable.run();
            return null;
        }

        @Override
        protected void done ()
        {
            if (responded)
            {
                component.setCursor(originalCursor);
                responded = false;
            }
        }
    }

}
