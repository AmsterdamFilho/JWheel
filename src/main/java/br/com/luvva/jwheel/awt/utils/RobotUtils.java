package br.com.luvva.jwheel.awt.utils;

import java.awt.*;

public final class RobotUtils
{

    public static int autoDelayMs = 40;
    public static boolean autoWaitForIdle = true;

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
        r.setAutoDelay(autoDelayMs);
        r.setAutoWaitForIdle(autoWaitForIdle);
        return r;
    }

}
