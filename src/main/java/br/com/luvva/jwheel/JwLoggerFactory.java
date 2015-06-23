package br.com.luvva.jwheel;

import org.slf4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@Singleton
public class JwLoggerFactory
{

    @Produces
    public Logger getLogger (InjectionPoint injectionPoint)
    {
        return org.slf4j.LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
