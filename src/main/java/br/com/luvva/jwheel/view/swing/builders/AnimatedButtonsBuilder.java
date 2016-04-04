package br.com.luvva.jwheel.view.swing.builders;

import br.com.luvva.jwheel.view.swing.extension.AlphaButton;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AnimatedButtonsBuilder
{

    private ArrayList<AlphaButton>      buttons          = new ArrayList<>();
    private Map<AlphaButton, Rectangle> originalsBounds  = new IdentityHashMap<>();
    private Map<Integer, Float>         opacityFactorMap = new HashMap<>();
    private Map<Integer, Float>         sizeFactorMap    = new HashMap<>();
    private float                       defaultOpacity   = 0.7f;

    {
        opacityFactorMap.put(0, 1f);
        opacityFactorMap.put(1, 0.85f);
        opacityFactorMap.put(2, 0.70f);
        opacityFactorMap.put(3, 0.55f);
        opacityFactorMap.put(4, 0.40f);
        opacityFactorMap.put(5, 0.35f);
        opacityFactorMap.put(6, 0.30f);

        sizeFactorMap.put(0, 1.12f);
        sizeFactorMap.put(1, 1.10f);
        sizeFactorMap.put(2, 1.08f);
        sizeFactorMap.put(3, 1.06f);
        sizeFactorMap.put(4, 1.04f);
        sizeFactorMap.put(5, 1.02f);
        sizeFactorMap.put(6, 1.00f);
    }

    public void setDefaultOpacity (float defaultOpacity)
    {
        this.defaultOpacity = defaultOpacity;
    }

    public void setSizeFactorMap (Map<Integer, Float> sizeFactorMap)
    {
        this.sizeFactorMap = sizeFactorMap;
    }

    public void setOpacityFactorMap (Map<Integer, Float> opacityFactorMap)
    {
        this.opacityFactorMap = opacityFactorMap;
    }

    @SuppressWarnings ("UnusedReturnValue")
    public AlphaButton addNewButton (String tooltipText, Rectangle bounds, Icon icon, ActionListener al)
    {
        AlphaButton button = addNewButton(bounds, al, tooltipText);
        button.setIcon(icon);
        buttons.add(button);
        return button;
    }

    @SuppressWarnings ("UnusedReturnValue")
    public AlphaButton addNewButton (String text, String tooltipText, Rectangle bounds, Icon icon,
                                     ActionListener al)
    {
        AlphaButton button = addNewButton(bounds, al, tooltipText);
        button.setText(text);
        button.setIcon(icon);
        buttons.add(button);
        return button;
    }

    @SuppressWarnings ("UnusedReturnValue")
    public AlphaButton addNewButton (String text, String tooltipText, Rectangle bounds, ActionListener al)
    {
        AlphaButton button = addNewButton(bounds, al, tooltipText);
        button.setText(text);
        buttons.add(button);
        return button;
    }

    public AlphaButton addNewButton (Rectangle bounds, ActionListener actionListener, String tooltipText)
    {
        AlphaButton button = new AlphaButton();

        button.setBounds(bounds);
        button.addActionListener(actionListener);
        button.setToolTipText(tooltipText);
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        originalsBounds.put(button, bounds);
        button.setAlpha(defaultOpacity);
        button.addMouseListener(new MyMouseListener());

        return button;
    }

    public List<AlphaButton> getButtons ()
    {
        return Collections.unmodifiableList(buttons);
    }

    private class MyMouseListener extends MouseAdapter
    {

        @Override
        public void mouseEntered (MouseEvent e)
        {
            int focusedButtonIndex = positionOnTheList((AlphaButton) e.getSource());
            // animate the buttons
            for (int buttonIndex = 0; buttonIndex < buttons.size(); buttonIndex++)
            {
                AlphaButton jButton = buttons.get(buttonIndex);
                int diff = Math.abs(buttonIndex - focusedButtonIndex);
                jButton.setAlpha(getOpacityFactor(diff));
                resize(jButton, getSizeFactor(diff));
            }
        }

        @Override
        public void mouseExited (MouseEvent e)
        {
            // inanimate
            buttons.forEach(b -> {
                b.setAlpha(defaultOpacity);
                b.setBounds(originalsBounds.get(b));
            });
        }

        private void resize (JButton button, float factor)
        {
            //noinspection SuspiciousMethodCalls
            Rectangle originalBounds = originalsBounds.get(button);
            int height = originalBounds.height;
            int width = originalBounds.width;
            int newHeight = (int) (height * factor);
            int newWidth = (int) (width * factor);
            int newY = originalBounds.y - ((newHeight - height) / 2);
            int newX = originalBounds.x - ((newWidth - width) / 2);
            button.setBounds(newX, newY, newWidth, newHeight);
        }

        private float getOpacityFactor (int diff)
        {
            Float opacity = opacityFactorMap.get(diff);
            if (opacity == null)
            {
                return 1.0f;
            }
            return opacity;
        }

        private float getSizeFactor (int diff)
        {
            Float sizeFactor = sizeFactorMap.get(diff);
            if (sizeFactor == null)
            {
                return 1.0f;
            }
            return sizeFactor;
        }

    }

    private int positionOnTheList (AlphaButton b)
    {
        for (int i = 0; i < buttons.size(); i++)
        {
            AlphaButton loopButton = buttons.get(i);
            if (b == loopButton)
            {
                return i;
            }
        }
        assert false : "The button wasn't on the list, but it should!";
        return 0;
    }

}