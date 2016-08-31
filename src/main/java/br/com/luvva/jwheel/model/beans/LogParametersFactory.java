package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.dao.xstream.LogParametersDao;
import org.slf4j.Logger;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LogParametersFactory implements ParameterFactory<LogParameters>
{
    private @Inject PathNamesProducer         pathNamesProducer;
    private @Inject PathParameters            pathParameters;
    private @Inject LogParametersDao          xStreamDao;
    private @Inject Logger                    logger;
    private @Inject XStreamParametersProducer producer;

    @Produces
    private LogParameters produce ()
    {
        return producer.produce(xStreamDao, logger, this);
    }

    @Override
    public void setDefaultParameters (LogParameters parameters)
    {
        parameters.setLoggerLevel(null);
        parameters.setLogFilePath(Paths.get(
                pathParameters.getAppDataDirectory(), pathNamesProducer.produceAppLogFileName() + ".log").toString());
        //@formatter:off
        parameters.setLoggerConfigurationXml(
                "<configuration>" +
                    "<appender name=\"FILE\" class=\"ch.qos.logback.core.FileAppender\">" +
                        "<file>" + parameters.getLogFilePath() + "</file>" +
                        "<encoder>" +
                            "<pattern>%date %level [%thread] %logger{15} [%file:%line] %msg%n</pattern>" +
                        "</encoder>" +
                    "</appender>" +
                    "<root level=\"WARN\">" +
                        "<appender-ref ref=\"FILE\" />" +
                    "</root>" +
                "</configuration>"
        );
        //@formatter:on
        parameters.setUseLoggerConfigurationXml(false);
    }
}
