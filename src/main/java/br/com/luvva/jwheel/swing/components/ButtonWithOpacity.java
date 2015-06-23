package br.com.luvva.jwheel.swing.components;

import javax.swing.*;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ButtonWithOpacity extends JButton
{
    private float opacity = 1f;

    public ButtonWithOpacity ()
    {
    }

    public ButtonWithOpacity (float opacity)
    {
        this.opacity = opacity;
    }

    public float getOpacity ()
    {
        return opacity;
    }

    public void setOpacity (float opacity)
    {
        this.opacity = opacity;
        repaint();
    }

    @Override
    public void paint (Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        super.paint(g2);
        g2.dispose();
    }
}
