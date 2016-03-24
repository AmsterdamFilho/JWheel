package br.com.luvva.jwheel.view.swing.utils;

import br.com.luvva.jwheel.model.utils.LongTaskManager;
import br.com.luvva.jwheel.model.utils.Wrapper;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class WaitingCursorAction implements ActionListener
{

    private Runnable  task;
    private int       allowedDelay;
    private Component component;
    private boolean   freezeUI;

    public WaitingCursorAction (Runnable task, int allowedDelay, Component component)
    {
        this(task, allowedDelay, component, true);
    }

    public WaitingCursorAction (Runnable task, int allowedDelay, Component component, boolean freezeUI)
    {
        this.task = task;
        this.allowedDelay = allowedDelay;
        this.component = component;
        this.freezeUI = freezeUI;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        runAction();
    }

    private synchronized void runAction ()
    {
        Wrapper<Boolean> cursorChanged = new Wrapper<>();
        cursorChanged.setValue(false);
        if (freezeUI)
        {
            LongTaskManager taskManager = new LongTaskManager(task, allowedDelay,
                    () -> {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        cursorChanged.setValue(true);
                    });
            taskManager.executeAndWait();
            if (cursorChanged.getValue())
            {
                component.setCursor(Cursor.getDefaultCursor());
            }
        }
        else
        {
            LongTaskManager taskManager = new LongTaskManager(task, allowedDelay,
                    () -> EventQueue.invokeLater(() -> {
                        component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        cursorChanged.setValue(true);
                    }));
            taskManager.executeAndReturn(() -> EventQueue.invokeLater(() -> {
                if (cursorChanged.getValue())
                {
                    component.setCursor(Cursor.getDefaultCursor());
                }
            }));
        }
    }

}
