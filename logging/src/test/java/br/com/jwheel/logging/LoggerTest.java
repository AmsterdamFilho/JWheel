package br.com.jwheel.logging;

import br.com.jwheel.test.WeldEnabledTest;
import org.junit.Test;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LoggerTest extends WeldEnabledTest
{
    private @Inject Logger logger;

    @Test
    public void test () throws Exception
    {
        logger.trace("Tracing");
        logger.debug("Debugging");
        logger.info("Informing");
        logger.warn("Warning");
        logger.error("Error...");
    }
}
