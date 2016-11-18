package br.com.luvva.jwheel;

import br.com.luvva.jwheel.core.cdi.WeldContext;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
public class WeldJUnit4Runner extends BlockJUnit4ClassRunner
{
    public WeldJUnit4Runner (Class<?> clazz) throws InitializationError
    {
        super(clazz);
    }

    @Override
    protected Object createTest ()
    {
        final Class<?> test = getTestClass().getJavaClass();
        return WeldContext.getInstance().getBean(test);
    }
}
