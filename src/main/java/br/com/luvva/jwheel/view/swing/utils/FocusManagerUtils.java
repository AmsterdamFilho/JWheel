package br.com.luvva.jwheel.view.swing.utils;

import javax.swing.*;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FocusManagerUtils
{
    public static void addEnterAsForwardTraversalKey (Component component)
    {
        Set<AWTKeyStroke> forwardKeys = component.getFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        Set<AWTKeyStroke> newForwardKeys = new HashSet<>(forwardKeys);
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        component.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
    }
}
