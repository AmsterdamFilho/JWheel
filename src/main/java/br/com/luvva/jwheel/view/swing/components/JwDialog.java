package br.com.luvva.jwheel.view.swing.components;

import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class JwDialog extends JDialog
{

    public JwDialog (boolean modal)
    {
        this(null, modal);
        configure();
    }

    public JwDialog (boolean modal, int width, int height)
    {
        this(null, modal, width, height);
    }

    public JwDialog (Component cp, boolean modal, int width, int height)
    {
        super(JOptionPane.getFrameForComponent(cp), modal);
        setSize(new Dimension(width, height));
        centralize(cp);
        configure();
    }

    public JwDialog (Component cp, boolean modal, Rectangle bounds)
    {
        super(JOptionPane.getFrameForComponent(cp), modal);
        setBounds(bounds);
        configure();
    }

    public JwDialog (Component cp, boolean modal)
    {
        super(JOptionPane.getFrameForComponent(cp), modal);
        configure();
    }

    private void configure ()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing (WindowEvent e)
            {
                dialogRequestedToClose();
            }
        });
    }

    public void centralize (Component comp)
    {
        if (comp == null)
        {
            setLocationRelativeTo(null);
            return;
        }
        Point pDialog = new Point();
        Point pComp = comp.getLocationOnScreen();

        pDialog.x = (comp.getSize().width - this.getSize().width) / 2;
        pDialog.y = (comp.getSize().height - this.getSize().height) / 2;

        if (pComp.x > 0)
        {
            pDialog.x += pComp.x;
        }
        if (pComp.y > 0)
        {
            pDialog.y += pComp.y;
        }

        setLocationRelativeTo(null);
        setLocation(pDialog);

    }

    @Override
    protected JRootPane createRootPane ()
    {
        JRootPane r = super.createRootPane();
        InputMap map = r.getInputMap(JRootPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        map.put(stroke, "Escape");
        r.getActionMap().put("Escape", new AbstractAction()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                dialogRequestedToClose();
            }

        });
        return r;
    }

    public abstract void dialogRequestedToClose ();
}
