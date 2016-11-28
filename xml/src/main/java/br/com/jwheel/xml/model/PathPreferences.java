package br.com.jwheel.xml.model;

import br.com.jwheel.core.java.SystemUtils;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.File;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class PathPreferences
{
    private String appDataDirectory;
    private String preferencesDirectory;

    @PostConstruct
    private void init ()
    {
        try
        {
            String appDataDirFromEnv = System.getenv(getEnvKey());
            if (appDataDirFromEnv == null)
            {
                setAppDataDirectoryAsDefault();
            }
            else
            {
                File appDataFile = Paths.get(appDataDirFromEnv).toFile();
                //noinspection ResultOfMethodCallIgnored
                appDataFile.mkdirs();
                if (appDataFile.isDirectory())
                {
                    appDataDirectory = appDataDirFromEnv;
                }
                else
                {
                    setAppDataDirectoryAsDefault();
                }
            }
        }
        catch (Exception ex)
        {
            setAppDataDirectoryAsDefault();
        }
        preferencesDirectory = Paths.get(getAppDataDirectory(), getPreferencesFolderName()).toString();
    }

    private void setAppDataDirectoryAsDefault ()
    {
        if (SystemUtils.isWindows())
        {
            appDataDirectory = Paths.get(System.getenv("APPDATA"), getRootFolderName()).toString();

        }
        else
        {
            appDataDirectory = Paths.get(System.getProperty("user.home"), "." + getRootFolderName()).toString();
        }
    }

    public String getPreferencesDirectory ()
    {
        return preferencesDirectory;
    }

    public String getAppDataDirectory ()
    {
        return appDataDirectory;
    }

    public File getPreferencesFile (String relativePath)
    {
        return Paths.get(getPreferencesDirectory(), relativePath + ".xml").toFile();
    }

    public String getPreferencesFolderName ()
    {
        return "prefs";
    }

    public String getRootFolderName ()
    {
        return "jwheel";
    }

    public String getEnvKey ()
    {
        return "JWHEEL";
    }
}
