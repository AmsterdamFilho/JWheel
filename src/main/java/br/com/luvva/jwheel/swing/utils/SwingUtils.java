package br.com.luvva.jwheel.swing.utils;

import br.com.luvva.jwheel.images.ImageProvider;
import br.com.luvva.jwheel.text.TextProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Singleton
public class SwingUtils
{

    @Inject
    private ImageProvider imageProvider;

    @Inject
    private TextProvider textProvider;

    public boolean getUserConfirmation (String msg)
    {
        return getUserConfirmation(msg, null);
    }

    public boolean getUserConfirmation (String msg, Component parentComponent)
    {
        MyJOptionPane optionPane = new MyJOptionPane(msg);
        optionPane.setIcon(imageProvider.getQuestionIcon());
        optionPane.setOptions(new Object[]{
                getBtn(optionPane, textProvider.getJOptionPane_Yes_Option(), JOptionPane.YES_OPTION),
                getBtn(optionPane, textProvider.getJOptionPane_No_Option(), JOptionPane.NO_OPTION)});

        JDialog dialog = optionPane.createDialog(parentComponent, "");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        dialog.dispose();
        return Integer.valueOf(JOptionPane.YES_OPTION).equals(optionPane.getValue());
    }

    private JButton getBtn (final JOptionPane optionPane, final String text, final Object value)
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

    private class MyJOptionPane extends JOptionPane
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
