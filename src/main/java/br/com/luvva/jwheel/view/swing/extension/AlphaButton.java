package br.com.luvva.jwheel.view.swing.extension;

import javax.swing.*;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AlphaButton extends JButton
{
    private float alpha;

    public AlphaButton ()
    {
        this(1.0f);
    }

    public AlphaButton (float alpha)
    {
        this.alpha = alpha;
        setOpaque(false);
    }

    public float getAlpha ()
    {
        return alpha;
    }

    public void setAlpha (float alpha)
    {
        this.alpha = alpha;
        repaint();
    }

    @Override
    public void paint (Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paint(g2);
        g2.dispose();
    }
}
