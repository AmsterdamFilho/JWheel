package br.com.luvva.jwheel.view.swing.components;

import br.com.luvva.jwheel.view.swing.utils.FocusManagerUtils;
import br.com.luvva.jwheel.view.swing.utils.UndoRedoDecorator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwTextField extends JTextField
{

    private @Inject UndoRedoDecorator undoRedoDecorator;

    public JwTextField ()
    {
        setColumns(0);
        FocusManagerUtils.addEnterAsForwardTraversalKey(this);
    }

    @PostConstruct
    private void init ()
    {
        undoRedoDecorator.decorate(this);
    }
}