package br.com.luvva.jwheel.swing.utils;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Creation date: 12/06/15
 * author Amsterdam Luís de Lima Filho <amsterdam@luvva.com.br>
 */
public class UndoRedoDecorator
{
    private UndoAction           undoAction  = new UndoAction ();
    private RedoAction           redoAction  = new RedoAction ();
    private UndoManager          undo        = new UndoManager ();
    private UndoableEditListener undoHandler = new MyUndoableEditListener ();
    private JTextComponent       textComp    = null;

    public UndoRedoDecorator ()
    {
    }

    public void undo ()
    {
        undoAction.actionPerformed (null);
    }

    public void redo ()
    {
        redoAction.actionPerformed (null);
    }

    public void decorate (JTextComponent txtCp)
    {
        this.textComp = txtCp;
        setInputMap ();
        txtCp.getDocument ().addUndoableEditListener (undoHandler);
    }

    public void decorate (Document doc)
    {
        doc.addUndoableEditListener (undoHandler);
        undo.discardAllEdits ();
        undoAction.updateUndoState ();
        redoAction.updateRedoState ();
    }

    private void setInputMap ()
    {
        InputMap inputMap = textComp.getInputMap ();

        KeyStroke key = KeyStroke.getKeyStroke (KeyEvent.VK_Z, Event.CTRL_MASK);
        inputMap.put (key, undoAction);

        key = KeyStroke.getKeyStroke (KeyEvent.VK_Y, Event.CTRL_MASK);
        inputMap.put (key, redoAction);
    }

    private class MyUndoableEditListener implements UndoableEditListener
    {
        @Override
        public void undoableEditHappened (UndoableEditEvent e)
        {
            undo.addEdit (e.getEdit ());
            undoAction.updateUndoState ();
            redoAction.updateRedoState ();
        }
    }

    private class UndoAction extends AbstractAction
    {
        private UndoAction ()
        {
            setEnabled (false);
        }

        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (textComp.isEnabled () && textComp.isEditable ())
            {
                try
                {
                    undo.undo ();
                }
                catch (CannotUndoException ex)
                {
                }
                updateUndoState ();
                redoAction.updateRedoState ();
            }
        }

        private void updateUndoState ()
        {
            if (undo.canUndo ())
            {
                setEnabled (true);
            }
            else
            {
            }
        }

    }

    private class RedoAction extends AbstractAction
    {
        private RedoAction ()
        {
            setEnabled (false);
        }

        @Override
        public void actionPerformed (ActionEvent e)
        {
            try
            {
                undo.redo ();
            }
            catch (CannotRedoException ex)
            {
            }
            updateRedoState ();
            undoAction.updateUndoState ();
        }

        private void updateRedoState ()
        {
            if (undo.canRedo ())
            {
                setEnabled (true);
            }
            else
            {
                setEnabled (false);
            }
        }
    }

}
