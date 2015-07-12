package br.com.luvva.jwheel;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class JwLoggerFactory
{

    private Logger logger = LoggerFactory.getLogger(JwLoggerFactory.class);

    @Produces
    public Logger getLogger (InjectionPoint injectionPoint)
    {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void configureLogbackAsDefault (String logFilePath)
    {
        configureLogbackAsDefault(logFilePath, Level.WARN);
    }

    public void configureLogbackAsDefault (String logFilePath, Level level)
    {
        //@formatter:off
        configureLogback(
                "<configuration>" +
                    "<appender name=\"FILE\" class=\"ch.qos.logback.core.FileAppender\">" +
                        "<file>" + logFilePath + "</file>" +
                        "<encoder>" +
                            "<pattern>%date %level [%thread] %logger{10} [%file:%line] %msg%n</pattern>" +
                        "</encoder>" +
                    "</appender>" +
                    "<root level=\"" + level.levelStr + "\">" +
                        "<appender-ref ref=\"FILE\" />" +
                    "</root>" +
                "</configuration>"
        );
        //@formatter:on
    }

    public void configureLogback (String xmlContent)
    {
        InputStream inputStream = null;
        try
        {
            inputStream = new ByteArrayInputStream(xmlContent.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("XmlContent can't be encoded to UTF-8", e);
        }
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try
        {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            // Call context.reset() to clear any previous configuration, e.g. default
            // configuration. For multi-step configuration, omit calling context.reset().
            context.reset();
            configurator.doConfigure(inputStream);
        }
        catch (JoranException je)
        {
            // StatusPrinter will handle this
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }

}
