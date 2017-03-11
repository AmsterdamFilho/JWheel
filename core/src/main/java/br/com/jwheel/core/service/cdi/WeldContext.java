package br.com.jwheel.core.service.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class WeldContext
{
    private static final WeldContext instance = new WeldContext();

    private final WeldContainer container;

    private WeldContext ()
    {
        Weld weld = new Weld();
        this.container = weld.initialize();
    }

    public static WeldContext getInstance ()
    {
        return instance;
    }

    public <T> T getAnyBean (Class<T> type)
    {
        return container.instance().select(type).get();
    }

    public <T> T getDefaultBean (Class<T> type)
    {
        return getBean(type, new AnnotationLiteral<Default>() {});
    }

    public <T> T getBean (Class<T> type, Annotation... qualifiers)
    {
        return container.instance().select(type, qualifiers).get();
    }
}
