package br.com.luvva.jwheel;

import br.com.luvva.jwheel.model.beans.LogParameters;
import ch.qos.logback.classic.Level;
import org.junit.runner.RunWith;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@RunWith (WeldJUnit4Runner.class)
public abstract class AbstractTest
{
    @PostConstruct
    private void init ()
    {
        LogParameters lp = WeldContext.getInstance().getBean(LogParameters.class, new AnnotationLiteral<Default>() {});
        WeldContext.getInstance().getBean(JwLoggerFactory.class)
                   .configureLogbackAsDefault(lp.getLogFilePath(), Level.INFO);
    }
}
