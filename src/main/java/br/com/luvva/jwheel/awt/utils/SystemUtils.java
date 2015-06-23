package br.com.luvva.jwheel.awt.utils;

import java.awt.*;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
public final class SystemUtils
{
    public static int getMenuShortcutMask ()
    {
        return Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask ();
    }

}