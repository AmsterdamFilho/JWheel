package br.com.luvva.jwheel.awt.utils;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * Test string should be all in lower caps. Test should run in OS X.
 */
public class RobotUtilsTest
{

    private Robot robot = RobotUtils.getRobot();
    private String testString = "this is a test of the robot utils class";

    @Test
    public void testKeyType () throws Exception
    {
        File testFile = File.createTempFile("jwheel", "robotUtilsTeste");
        testFile.deleteOnExit();
        ProcessBuilder pb = new ProcessBuilder(Arrays.asList("open", testFile.getAbsolutePath()));
        pb.redirectErrorStream(true);
        new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground () throws Exception
            {
                Thread.sleep(2000);
                type(testString);
                RobotUtils.keyType(KeyEvent.VK_Q, KeyEvent.VK_META);
                return null;
            }
        }.execute();
        int waitFor = pb.start().waitFor();
        if (waitFor == 0)
        {
        }
        else
        {
            throw new Exception("Process did not end well. Code: " + waitFor);
        }
        Thread.sleep(30000);
        Assert.assertEquals(testString, new String(Files.readAllBytes(testFile.toPath())));
    }

    private void type (int i) throws AWTException
    {
        robot.delay(500);
        RobotUtils.keyType(i);
    }

    private void type (String s) throws AWTException
    {
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            // keycode only handles [a-z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123)
            {
                code = code - 32;
            }
            robot.delay(200);
            RobotUtils.keyType(code);
        }
    }
}