package br.com.jwheel.javafx.adapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class ControlAdapter<N extends Node, T>
{
    private final ObjectProperty<T> valueProperty = new SimpleObjectProperty<>();

    public ObjectProperty<T> valueProperty ()
    {
        return valueProperty;
    }

    private N control;

    public void adapt (N control)
    {
        if (control == null)
        {
            reset();
        }
        else if (this.control == null)
        {
            privateAdapt(control);
        }
        else if (this.control != control)
        {
            reset();
            privateAdapt(control);
        }
        // this.control == control, so nothing needs to be done
    }

    public void reset ()
    {
        if (control != null)
        {
            resetImpl();
            control = null;
        }
    }

    private void privateAdapt (N control)
    {
        this.control = control;
        adaptImpl();
    }

    public N getControl ()
    {
        return control;
    }

    protected abstract void resetImpl ();

    protected abstract void adaptImpl ();
}
