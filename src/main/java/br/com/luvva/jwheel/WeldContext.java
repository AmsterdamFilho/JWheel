package br.com.luvva.jwheel;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class WeldContext
{
    private static final WeldContext instance = new WeldContext();

    private final Weld          weld;
    private final WeldContainer container;

    private WeldContext ()
    {
        this.weld = new Weld();
        this.container = weld.initialize();
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run ()
            {
                weld.shutdown();
            }
        });
    }

    public static WeldContext getInstance ()
    {
        return instance;
    }

    public <T> T getBean (Class<T> type)
    {
        return container.instance().select(type).get();
    }
}
