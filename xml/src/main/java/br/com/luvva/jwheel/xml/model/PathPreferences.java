package br.com.luvva.jwheel.xml.model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class PathPreferences
{
    private @Inject PathNamesProducer pathNamesProducer;

    private String appDataDirectory;
    private String preferencesDirectory;

    @PostConstruct
    private void init ()
    {
        setAppDataDirectory(
                Paths.get(System.getProperty("user.home"), pathNamesProducer.produceAppDataFolderName()).toString());
        setPreferencesDirectory(
                Paths.get(getAppDataDirectory(), pathNamesProducer.produceAppPreferencesFolderName()).toString());
    }

    public String getPreferencesDirectory ()
    {
        return preferencesDirectory;
    }

    public void setPreferencesDirectory (String preferencesDirectory)
    {
        this.preferencesDirectory = preferencesDirectory;
    }

    public String getAppDataDirectory ()
    {
        return appDataDirectory;
    }

    public void setAppDataDirectory (String appDataDirectory)
    {
        this.appDataDirectory = appDataDirectory;
    }

    public File getParametersFile (String relativePath)
    {
        return Paths.get(getPreferencesDirectory(), relativePath + ".xml").toFile();
    }
}
