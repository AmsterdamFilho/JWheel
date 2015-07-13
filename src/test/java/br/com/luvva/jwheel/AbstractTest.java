package br.com.luvva.jwheel;

import org.junit.runner.RunWith;

import javax.annotation.PostConstruct;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@RunWith (WeldJUnit4Runner.class)
public abstract class AbstractTest
{
    @PostConstruct
    private void init ()
    {
        WeldContext.getInstance().getBean(JwLoggerFactory.class).configureLogbackAsDefault(JWheel.NAME + ".log");
    }
}
