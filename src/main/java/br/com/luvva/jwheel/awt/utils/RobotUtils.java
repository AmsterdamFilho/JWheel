package br.com.luvva.jwheel.awt.utils;

import java.awt.*;

public class RobotUtils
{

    public static void keyType (int keyCode)
    {
        Robot r;
        try
        {
            r = new Robot ();
            r.keyPress (keyCode);
            r.keyRelease (keyCode);
        }
        catch (AWTException ex)
        {
        }
    }

    public static void keyType (int keyCode, int... modifiers)
    {
        Robot r;
        try
        {
            r = new Robot ();
            for (int modifier : modifiers)
            {
                r.keyPress (modifier);
            }
            r.keyPress (keyCode);
            r.keyRelease (keyCode);
            for (int modifier : modifiers)
            {
                r.keyRelease (modifier);
            }
        }
        catch (AWTException ex)
        {
        }
    }
}
