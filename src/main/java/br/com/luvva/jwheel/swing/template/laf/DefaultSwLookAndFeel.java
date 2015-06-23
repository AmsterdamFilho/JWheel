package br.com.luvva.jwheel.swing.template.laf;

import javax.enterprise.context.ApplicationScoped;
import javax.swing.*;
import javax.swing.border.Border;

@ApplicationScoped
public class DefaultSwLookAndFeel implements SwLookAndFeel
{

    private Border defaultBorder = new BlackAndWhiteEtchedBorder();

    @Override
    public Border getDefaultBorder ()
    {
        return defaultBorder;
    }

    @Override
    public JSeparator createHorizontalJSeparator ()
    {
        JSeparator jSeparator = new JSeparator();
        jSeparator.setOrientation(JSeparator.HORIZONTAL);
        return jSeparator;
    }

    @Override
    public JSeparator createVerticalJSeparator ()
    {
        JSeparator jSeparator = new JSeparator();
        jSeparator.setOrientation(JSeparator.VERTICAL);
        return jSeparator;
    }

}
