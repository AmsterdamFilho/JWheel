package br.com.luvva.jwheel.control;

import br.com.luvva.jwheel.AbstractTest;
import br.com.luvva.jwheel.WeldContext;
import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JWheelMainTest extends AbstractTest
{

    @Test
    public void test ()
    {
        WeldContext.getInstance().getBean(JWheelMain.class);
    }

}