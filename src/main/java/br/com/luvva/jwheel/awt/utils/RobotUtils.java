package br.com.luvva.jwheel.awt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public final class RobotUtils
{

    public static int     autoDelayMs     = 40;
    public static boolean autoWaitForIdle = true;

    private static Robot robot;

    private final static Logger logger = LoggerFactory.getLogger(RobotUtils.class);

    static
    {
        try
        {
            createNewRobot();
        }
        catch (AWTException e)
        {
            logger.error("", e);
        }
    }

    private RobotUtils ()
    {

    }

    public static Robot getRobot ()
    {
        return robot;
    }

    public static void keyType (int keyCode)
    {
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    public static void keyType (int keyCode, int... modifiers)
    {
        for (int modifier : modifiers)
        {
            robot.keyPress(modifier);
        }
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
        for (int modifier : modifiers)
        {
            robot.keyRelease(modifier);
        }
    }

    public static void createNewRobot () throws AWTException
    {
        robot = new Robot();
        robot.setAutoDelay(autoDelayMs);
        robot.setAutoWaitForIdle(autoWaitForIdle);
    }

    public static void setRobot (Robot robot)
    {
        RobotUtils.robot = robot;
    }
}
