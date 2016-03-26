package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.view.swing.laf.SwLookAndFeel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class GridBagLayoutPanel extends JPanel
{
    private         GridBagConstraints constraints;
    private @Inject SwLookAndFeel      lookAndFeel;

    public GridBagLayoutPanel ()
    {
        super(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
    }

    public void addWithConstraints (Component component)
    {
        if (constraints.gridx > 0)
        {
            constraints.insets.left = lookAndFeel.leftInsetsBetweenComponents();
        }
        if (constraints.gridy > 0)
        {
            constraints.insets.top = lookAndFeel.topInsetsBetweenComponents();
        }
        add(component, constraints);
        constraints.insets.left = 0;
        constraints.insets.top = 0;
    }

    public void add (Component component, String label)
    {
        add(component, label, SwingConstants.LEFT);
    }

    public void add (Component component, String label, int alignment)
    {
        addWithConstraints(createLabelPanel(component, label, alignment));
    }

    protected LabeledPanel createLabelPanel (Component component, String text, int alignment)
    {
        LabeledPanel labeledPanel = WeldContext.getInstance().getBean(LabeledPanel.class);
        labeledPanel.setComponent(component, text, alignment);
        return labeledPanel;
    }

    public GridBagConstraints getConstraints ()
    {
        return constraints;
    }
}
