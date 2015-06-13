package br.com.luvva.jwheel.awt.utils;

import java.awt.*;

public class SystemUtils
{
    public static int getMenuShortcutMask ()
    {
        return Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask ();
    }

}
