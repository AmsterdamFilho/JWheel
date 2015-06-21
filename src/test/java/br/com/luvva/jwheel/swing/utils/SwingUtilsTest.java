package br.com.luvva.jwheel.swing.utils;

import org.junit.Assert;
import org.junit.Test;

public class SwingUtilsTest
{

    @Test
    public void testGetUserConfirmation () throws Exception
    {
        Assert.assertEquals(SwingUtils.getUserConfirmation("Do you wish the see the test result?"), true);
        Assert.assertEquals(SwingUtils.getUserConfirmation("Do you wish the see the test fail?"), false);
    }
}