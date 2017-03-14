package br.com.jwheel.test;

import br.com.jwheel.cdi.WeldContext;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
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
        return WeldContext.getInstance().getAny(test);
    }
}
