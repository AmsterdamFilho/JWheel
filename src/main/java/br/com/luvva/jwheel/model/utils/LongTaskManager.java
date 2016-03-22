package br.com.luvva.jwheel.model.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This is an utility class whose intent is to simplify the execution of a task, represented by a Runnable, that
 * may take too long. If that happens, the LongTaskManager provides a way for the client class to react without
 * interrupting the task.
 * <p>
 * The task is always executed in a daemon thread.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LongTaskManager
{
    // control
    private boolean timerExpired = false;
    private boolean taskFinished = false;

    //settings
    private Runnable task;
    private int      acceptableDuration;
    private Runnable taskTakesTooLongHandler;

    public LongTaskManager (Runnable task, int acceptableDuration, Runnable taskTakesTooLongHandler)
    {
        this.task = task;
        this.acceptableDuration = acceptableDuration;
        this.taskTakesTooLongHandler = taskTakesTooLongHandler;
    }

    public synchronized void executeAndWait ()
    {
        createTaskThread().start();
        new TaskTimer().start();
        boolean timerListenerNotified = false;
        while (!taskFinished)
        {
            try
            {
                wait();
            }
            catch (InterruptedException ignored)
            {
            }
            if (timerExpired && !timerListenerNotified)
            {
                taskTakesTooLongHandler.run();
                timerListenerNotified = true;
            }
        }
    }

    public synchronized void executeAndReturn (Runnable taskListener)
    {
        createTaskThread(taskListener::run).start();
        new TaskTimer().start(() -> {
            if (!taskFinished)
            {
                taskTakesTooLongHandler.run();
            }
        });
    }

    private synchronized void setTimerExpired ()
    {
        this.timerExpired = true;
        notify();
    }

    private synchronized void setTaskFinished ()
    {
        this.taskFinished = true;
        notify();
    }

    private Thread createTaskThread ()
    {
        return createTaskThread(() -> {
        });
    }

    private Thread createTaskThread (Runnable actionAfterTaskRuns)
    {
        Thread taskThread = new Thread(() -> {
            task.run();
            setTaskFinished();
            actionAfterTaskRuns.run();
        });
        taskThread.setDaemon(true);
        return taskThread;
    }

    private class TaskTimer extends Timer
    {
        public TaskTimer ()
        {
            super(true);
        }

        private void start ()
        {
            start(() -> {
            });
        }

        private void start (Runnable actionAfterTimerExpired)
        {
            schedule(new TimerTask()
            {
                @Override
                public void run ()
                {
                    setTimerExpired();
                    actionAfterTimerExpired.run();
                }
            }, acceptableDuration);
        }
    }

}
