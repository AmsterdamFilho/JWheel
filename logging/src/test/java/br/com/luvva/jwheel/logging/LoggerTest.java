package br.com.luvva.jwheel.logging;

import br.com.luvva.jwheel.WeldEnabledTest;
import br.com.luvva.jwheel.core.cdi.WeldContext;
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
        WeldContext.getInstance().getBean(Logger.class);
    }
}
