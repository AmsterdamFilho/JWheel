package br.com.jwheel.logging;

import br.com.jwheel.xml.model.PathPreferences;
import ch.qos.logback.classic.Level;

import javax.inject.Inject;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DefaultLogPreferencesProvider
{
    private @Inject PathPreferences pathPreferences;

    public LogPreferences provide ()
    {
        LogPreferences response = new LogPreferences();
        setDefaultPreferences(response);
        return response;
    }

    protected void setDefaultPreferences (LogPreferences parameters)
    {
        parameters.setUseLoggerConfigurationXml(false);
        parameters.setLoggerLevel(Level.WARN.levelStr);
        parameters.setLogFilePath(getLogFilePath().toString());
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
    }

    private Path getLogFilePath ()
    {
        Path appDataDirectory = pathPreferences.getAppDataDirectory();
        return Paths.get(appDataDirectory.toString(), pathPreferences.getRootFolderName() + ".log");
    }
}
