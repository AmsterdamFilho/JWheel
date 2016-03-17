package br.com.luvva.jwheel.view.swing.components;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SpacedPanel extends JPanel
{
    public SpacedPanel (Component jc, int hGap, int vGap)
    {
        this(jc, vGap, vGap, hGap, hGap);
    }

    public SpacedPanel (Component jc, int upGap, int downGap, int leftGap, int rightGap)
    {
        setLayout(new BorderLayout());
        add(Box.createVerticalStrut(upGap), BorderLayout.PAGE_START);
        add(Box.createHorizontalStrut(leftGap), BorderLayout.LINE_START);
        add(jc, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(rightGap), BorderLayout.LINE_END);
        add(Box.createVerticalStrut(downGap), BorderLayout.PAGE_END);
    }

}
