package br.com.luvva.jwheel.view.swing.utils;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class TextChangedListener implements DocumentListener
{

    @Override
    public void insertUpdate (DocumentEvent e)
    {
        textChanged(e);
    }

    @Override
    public void removeUpdate (DocumentEvent e)
    {
        textChanged(e);
    }

    @Override
    public void changedUpdate (DocumentEvent e)
    {
        textChanged(e);
    }

    @SuppressWarnings ("UnusedParameters")
    public abstract void textChanged (DocumentEvent de);
}
