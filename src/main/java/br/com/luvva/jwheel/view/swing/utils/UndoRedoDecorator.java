package br.com.luvva.jwheel.view.swing.utils;

import br.com.luvva.jwheel.awt.utils.AwtUtils;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class UndoRedoDecorator
{
    private final UndoAction  undoAction  = new UndoAction();
    private final RedoAction  redoAction  = new RedoAction();
    private final UndoManager undoManager = new UndoManager();
    private JTextComponent textComp;

    public void decorate (JTextComponent textComp)
    {
        this.textComp = textComp;
        setInputMap();
        UndoableEditListener undoHandler = new MyUndoableEditListener();
        textComp.getDocument().addUndoableEditListener(undoHandler);
    }

    public void undo ()
    {
        undoAction.actionPerformed(null);
    }

    public void redo ()
    {
        redoAction.actionPerformed(null);
    }

    @SuppressWarnings ("MagicConstant")
    private void setInputMap ()
    {
        InputMap inputMap = textComp.getInputMap();

        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, AwtUtils.getMenuShortcutKeyMask());
        inputMap.put(key, undoAction);

        key = KeyStroke.getKeyStroke(KeyEvent.VK_Y, AwtUtils.getMenuShortcutKeyMask());
        inputMap.put(key, redoAction);
    }

    private class MyUndoableEditListener implements UndoableEditListener
    {
        @Override
        public void undoableEditHappened (UndoableEditEvent e)
        {
            undoManager.addEdit(e.getEdit());
            undoAction.updateUndoState();
            redoAction.updateRedoState();
        }
    }

    private class UndoAction extends AbstractAction
    {
        private UndoAction ()
        {
            setEnabled(false);
        }

        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (textComp.isEnabled() && textComp.isEditable())
            {
                undoManager.undo();
                updateUndoState();
                redoAction.updateRedoState();
            }
        }

        private void updateUndoState ()
        {
            if (undoManager.canUndo())
            {
                setEnabled(true);
            }
            else
            {
                setEnabled(false);
            }
        }

    }

    private class RedoAction extends AbstractAction
    {
        private RedoAction ()
        {
            setEnabled(false);
        }

        @Override
        public void actionPerformed (ActionEvent e)
        {
            undoManager.redo();
            updateRedoState();
            undoAction.updateUndoState();
        }

        private void updateRedoState ()
        {
            if (undoManager.canRedo())
            {
                setEnabled(true);
            }
            else
            {
                setEnabled(false);
            }
        }
    }

}