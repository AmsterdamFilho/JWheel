package br.com.luvva.jwheel;

import br.com.luvva.jwheel.model.beans.LogParameters;
import org.junit.runner.RunWith;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@RunWith (WeldJUnit4Runner.class)
public abstract class AbstractTest
{

    private @Inject JwLoggerFactory jwLoggerFactory;
    private @Inject LogParameters   logParameters;

    @PostConstruct
    private void init ()
    {
        jwLoggerFactory.configure(logParameters);
    }
}
