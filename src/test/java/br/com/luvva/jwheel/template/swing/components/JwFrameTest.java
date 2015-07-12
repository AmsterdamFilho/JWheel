package br.com.luvva.jwheel.template.swing.components;

import br.com.luvva.jwheel.AbstractTest;
import br.com.luvva.jwheel.JwLoggerFactory;
import br.com.luvva.jwheel.WeldContext;
import org.junit.Test;

import javax.inject.Inject;

public class JwFrameTest extends AbstractTest
{

    private @Inject JwLoggerFactory jwLoggerFactory;

    @Test
    public void test () throws InterruptedException
    {
        jwLoggerFactory.configureLogbackAsDefault("jwheel");
        JwFrame bean = WeldContext.getInstance().getBean(JwFrame.class);
        bean.setVisible(true);
//        Thread.sleep(10000000);
    }

}