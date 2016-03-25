package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.view.swing.laf.SwLookAndFeel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LabeledPanel extends JPanel
{

    private         JLabel        label;
    private         Component     component;
    private @Inject SwLookAndFeel lookAndFeel;

    public void setComponent (Component component)
    {
        setComponent(component, "");
    }

    public void setComponent (Component component, String text)
    {
        setComponent(component, text, SwingConstants.LEFT);
    }

    public void setComponent (Component component, String text, int alignment)
    {
        if (component == null)
        {
            throw new NullPointerException();
        }
        String notNullText = text == null ? "" : text;
        setLayout(new BorderLayout(0, lookAndFeel.verticalSpaceBetweenLabelAndComponent()));
        switch (alignment)
        {
            case SwingConstants.LEADING:
            case SwingConstants.LEFT:
                label = createLabel(" " + notNullText, alignment);
                break;
            default:
                label = createLabel(notNullText, alignment);
                break;
        }
        add(label, BorderLayout.PAGE_START);
        add(component, BorderLayout.CENTER);
    }

    protected JLabel createLabel (String text, int alignment)
    {
        return new BoldLabel(text, alignment);
    }

    public JLabel getLabel ()
    {
        return label;
    }

    @Override
    public boolean requestFocusInWindow ()
    {
        return component.requestFocusInWindow();
    }

}
