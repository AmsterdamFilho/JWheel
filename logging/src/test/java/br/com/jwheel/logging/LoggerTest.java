package br.com.jwheel.logging;

import br.com.jwheel.cdi.WeldContext;
import br.com.jwheel.test.WeldEnabledTest;
import org.junit.Test;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LoggerTest extends WeldEnabledTest
{
    private @Inject JwLoggerFactory jwLoggerFactory;

    @PostConstruct
    private void init ()
    {
        jwLoggerFactory.init();
    }

    @Test
    public void test () throws Exception
    {
        WeldContext.getInstance().getAny(Logger.class);
    }
}
