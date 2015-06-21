package br.com.luvva.jwheel.swing.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static br.com.luvva.jwheel.JWheelResourcesFactory.imageResourceProvider;
import static br.com.luvva.jwheel.JWheelResourcesFactory.textProvider;

public final class SwingUtils
{
    private SwingUtils ()
    {
    }

    public static boolean getUserConfirmation (String msg)
    {
        return getUserConfirmation(msg, null);
    }

    public static boolean getUserConfirmation (String msg, Component parentComponent)
    {
        MyJOptionPane optionPane = new MyJOptionPane(msg);
        optionPane.setIcon(imageResourceProvider.getQuestionIcon());
        optionPane.setOptions(new Object[]{
                getBtn(optionPane, textProvider.JOptionPane_Yes_Option(), JOptionPane.YES_OPTION),
                getBtn(optionPane, textProvider.JOptionPane_Yes_Option(), JOptionPane.NO_OPTION)});

        JDialog dialog = optionPane.createDialog(parentComponent, "");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        dialog.setUndecorated(true);
        dialog.dispose();
        return Integer.valueOf(JOptionPane.YES_OPTION).equals(optionPane.getValue());
    }

    private static JButton getBtn (final JOptionPane optionPane, final String text, final Object value)
    {
        final JButton jButton = new JButton(text);
        jButton.addActionListener((actionEvent) -> optionPane.setValue(value));
        jButton.addKeyListener(new KeyAdapter()
        {
            private boolean pressed = false;

            @Override
            public void keyPressed (KeyEvent e)
            {
                pressed = true;
            }

            @Override
            public void keyReleased (KeyEvent e)
            {
                if ((e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode()
                        == KeyEvent.VK_SPACE) && pressed)
                {
                    jButton.doClick();
                    pressed = false;
                }
            }

        });
        return jButton;
    }

    private static class MyJOptionPane extends JOptionPane
    {
        {
            InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            im.put(KeyStroke.getKeyStroke("released ESCAPE"),
                    im.get(KeyStroke.getKeyStroke("pressed ESCAPE")));
            im.put(KeyStroke.getKeyStroke("pressed ESCAPE"), "none");
        }

        private MyJOptionPane (Object message, int messageType, int optionType, Icon icon, Object[] options,
                               Object initialValue)
        {
            super(message, messageType, optionType, icon, options, initialValue);
        }

        private MyJOptionPane (Object message, int messageType, int optionType, Icon icon, Object[] options)
        {
            super(message, messageType, optionType, icon, options);
        }

        private MyJOptionPane (Object message, int messageType, int optionType, Icon icon)
        {
            super(message, messageType, optionType, icon);
        }

        private MyJOptionPane (Object message, int messageType, int optionType)
        {
            super(message, messageType, optionType);
        }

        private MyJOptionPane (Object message, int messageType)
        {
            super(message, messageType);
        }

        private MyJOptionPane (Object message)
        {
            super(message);
        }

        private MyJOptionPane ()
        {
        }

    }

}
