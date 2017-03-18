package br.com.jwheel.logging;

import br.com.jwheel.xml.model.FromXmlPreferences;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public final class JwLoggerFactory
{
    private final Logger logger = LoggerFactory.getLogger(JwLoggerFactory.class);

    private @Inject @FromXmlPreferences LogPreferences logPreferences;

    @PostConstruct
    private void init ()
    {
        configure(logPreferences);
    }

    @Produces
    public Logger getLogger (InjectionPoint injectionPoint)
    {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void configure (LogPreferences logPreferences)
    {
        Level loggerLevel = Level.toLevel(logPreferences.getLoggerLevel(), Level.WARN);
        if (logPreferences.isUseLoggerConfigurationXml())
        {
            configureLogback(logPreferences.getLoggerConfigurationXml());
        }
        else
        {
            configureLogbackAsDefault(logPreferences.getLogFilePath(), loggerLevel);
        }
    }

    private void configureLogbackAsDefault (String logFilePath, Level level)
    {
        if (logFilePath == null || level == null)
        {
            logger.error("Neither logFilePath nor level can be null!");
            return;
        }
        //@formatter:off
        configureLogback("" +
                        "<configuration>" +
                            "<appender name=\"FILE\" class=\"ch.qos.logback.core.FileAppender\">" +
                                "<file>" + logFilePath + "</file>" +
                                    "<encoder>" +
                                        "<pattern>%date %level [%thread] %logger{15} [%file:%line] %msg%n</pattern>" +
                                    "</encoder>" +
                            "</appender>" +
                                "<root level=\"" + level.levelStr + "\">" +
                                    "<appender-ref ref=\"FILE\" />" +
                                "</root>" +
                        "</configuration>"
        );
        //@formatter:on
    }

    private void configureLogback (String xmlContent)
    {
        if (xmlContent == null)
        {
            logger.error("XmlContent should not be null!");
            return;
        }
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
