package br.com.luvva.jwheel.model.providers;

import br.com.luvva.jwheel.AbstractTest;
import org.junit.Test;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LanguageTest extends AbstractTest
{
    private @Inject TextProvider textProvider;
    private @Inject Logger       logger;

    @Test
    public void test ()
    {
        logger.info("Current text provider: " + textProvider.getClass().getName());
    }
}
