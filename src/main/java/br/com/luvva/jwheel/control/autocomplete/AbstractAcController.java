package br.com.luvva.jwheel.control.autocomplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class AbstractAcController implements AcController
{
    private Object[] selected;
    private List<AcListener> listeners = new ArrayList<>();

    @Override
    public void setSelectedOption (Object[] option)
    {
        if (!Arrays.equals(selected, option))
        {
            selected = option;
            listeners.forEach(AcListener::selectedOptionChanged);
        }
    }

    @Override
    public void addListener (AcListener listener)
    {
        listeners.add(listener);
    }

    @Override
    public void removeListener (AcListener listener)
    {
        listeners.remove(listener);
    }

    protected Object[] getSelected ()
    {
        return selected;
    }
}
