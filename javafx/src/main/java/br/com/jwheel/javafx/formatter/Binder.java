package br.com.jwheel.javafx.formatter;

import javafx.scene.Node;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class Binder<T extends Node>
{
    private T node;

    public void bind (T node)
    {
        if (node == null)
        {
            unbind();
        }
        else if (this.node == null)
        {
            privateBind(node);
        }
        else if (this.node != node)
        {
            unbind();
            privateBind(node);
        }
        // this.node == node, so nothing needs to be done
    }

    public void unbind ()
    {
        if (node != null)
        {
            unbindImpl();
            node = null;
        }
    }

    private void privateBind (T node)
    {
        this.node = node;
        bindImpl();
    }

    public T getNode ()
    {
        return node;
    }

    protected abstract void unbindImpl ();

    protected abstract void bindImpl ();
}
