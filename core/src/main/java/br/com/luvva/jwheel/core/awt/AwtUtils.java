package br.com.luvva.jwheel.core.awt;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AwtUtils
{
    private static final int menuShortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private static final int controlShortcutMask = KeyEvent.CTRL_DOWN_MASK;

    public static int getMenuShortcutKeyMask ()
    {
        return menuShortcutKeyMask;
    }

    public static int getControlShortcutMask ()
    {
        return controlShortcutMask;
    }
}