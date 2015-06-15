package br.com.luvva.jwheel.awt.utils;

import java.awt.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class RobotUtils
{

    private RobotUtils ()
    {

    }

    public static void keyType (int keyCode) throws AWTException
    {
        Robot r = newRobot();
        r.keyPress(keyCode);
        r.keyRelease(keyCode);
    }

    public static void keyType (int keyCode, int... modifiers) throws AWTException
    {
        Robot r = newRobot();
        for (int modifier : modifiers)
        {
            r.keyPress(modifier);
        }
        r.keyPress(keyCode);
        r.keyRelease(keyCode);
        for (int modifier : modifiers)
        {
            r.keyRelease(modifier);
        }
    }

    private static Robot newRobot () throws AWTException
    {
        Robot r = new Robot();
        r.setAutoDelay(40);
        r.setAutoWaitForIdle(true);
        return r;
    }

}
