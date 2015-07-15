package br.com.luvva.jwheel;

import br.com.luvva.jwheel.model.beans.LocalParameters;
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
        LocalParameters lp = WeldContext.getInstance().getBean(LocalParameters.class, new AnnotationLiteral<Default>()
        {
        });
        WeldContext.getInstance().getBean(JwLoggerFactory.class)
                   .configureLogbackAsDefault(lp.getLogFilePath());
    }
}
