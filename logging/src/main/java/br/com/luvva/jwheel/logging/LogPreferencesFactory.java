package br.com.luvva.jwheel.logging;

import br.com.luvva.jwheel.xml.model.PathPreferences;
import br.com.luvva.jwheel.xml.model.PreferencesFactoryFromXml;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LogPreferencesFactory extends PreferencesFactoryFromXml<LogPreferences>
{
    private @Inject PathPreferences   pathPreferences;
    private @Inject LogPreferencesDao xStreamDao;

    @Produces
    private LogPreferences produce ()
    {
        return produce(xStreamDao);
    }

    @Override
    public void setDefaultPreferences (LogPreferences parameters)
    {
        parameters.setLoggerLevel(null);
        parameters.setLogFilePath(getLogFilePath().toString());
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

    private Path getLogFilePath ()
    {
        String appDataDirectory = pathPreferences.getAppDataDirectory();
        String logFileName = new File(appDataDirectory).getName();
        return Paths.get(appDataDirectory, logFileName + ".log");
    }
}
