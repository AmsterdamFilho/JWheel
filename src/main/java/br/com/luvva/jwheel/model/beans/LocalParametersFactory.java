package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.cdi.utils.NewInstance;
import br.com.luvva.jwheel.dao.xstream.LocalParametersDao;
import org.slf4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.FileSystems;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class LocalParametersFactory
{
    private @Inject Logger             logger;
    private @Inject LocalParametersDao localParametersDao;

    @Produces
    protected LocalParameters getLocalParameters ()
    {
        try
        {
            LocalParameters lpFromXml = localParametersDao.find();
            if (lpFromXml != null)
            {
                return lpFromXml;
            }
        }
        catch (Exception ex)
        {
            logger.error("Could not deserialize local parameters file!", ex);
        }
        LocalParameters localParameters = createDefaultLocalParameters();
        try
        {
            localParametersDao.merge(localParameters);
        }
        catch (Exception ex)
        {
            logger.error("Could not serialize local parameters!", ex);
        }
        return localParameters;
    }

    private LocalParameters createDefaultLocalParameters ()
    {
        LocalParameters lp = WeldContext.getInstance().getBean(LocalParameters.class,
                new AnnotationLiteral<NewInstance>()
                {
                });
        setDefaultPathParameters(lp);
        setDefaultConnectionParameters(lp);
        setDefaultLoggerParameters(lp);
        return lp;
    }

    protected void setDefaultPathParameters (LocalParameters lp)
    {
        String separator = FileSystems.getDefault().getSeparator();
        String appName = WeldContext.getInstance().getBean(ProductData.class).getProductName();
        String appDataPath = System.getProperty("user.home") + separator + appName;
        lp.setAppDataDirectory(appDataPath);
        lp.setLogFilePath(appDataPath + separator + appName + ".log");
    }

    protected void setDefaultConnectionParameters (LocalParameters lp)
    {
        lp.setDatabaseDriver("org.postgresql.Driver");
        lp.setDatabasePassword("postgres");
        lp.setDatabaseUrl("jdbc:postgresql://localhost:5433/postgres");
        lp.setDatabaseUser("postgres");
    }

    protected void setDefaultLoggerParameters (LocalParameters lp)
    {
        lp.setLoggerLevel(null);
        //@formatter:off
        lp.setLoggerConfigurationXml(
                "<configuration>" +
                    "<appender name=\"FILE\" class=\"ch.qos.logback.core.FileAppender\">" +
                        "<file>" + lp.getLogFilePath() + "</file>" +
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
        lp.setUseLoggerConfigurationXml(false);
    }

}
