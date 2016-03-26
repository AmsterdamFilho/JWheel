package br.com.luvva.jwheel.awt.utils;

import java.awt.Toolkit;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AwtUtils
{

    private static int menuShortcutMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    public static int getMenuShortcutKeyMask ()
    {
        return menuShortcutMask;
    }

}