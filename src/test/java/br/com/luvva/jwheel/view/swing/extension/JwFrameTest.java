package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.AbstractTest;
import br.com.luvva.jwheel.WeldContext;
import org.junit.Test;

public class JwFrameTest extends AbstractTest
{

    @Test
    public void test () throws InterruptedException
    {
        JwFrame bean = WeldContext.getInstance().getBean(JwFrame.class);
        bean.setVisible(true);
//        Thread.sleep(10000000);
    }

}