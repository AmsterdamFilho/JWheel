package br.com.jwheel.xml.model;

import br.com.jwheel.utils.SystemUtils;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class PathPreferences
{
    private Path appDataDirectory;
    private Path preferencesDirectory;

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
                Path appDataDirectoryPath = Paths.get(appDataDirFromEnv);
                Files.createDirectories(appDataDirectoryPath);
                if (Files.isDirectory(appDataDirectoryPath))
                {
                    appDataDirectory = appDataDirectoryPath;
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
        preferencesDirectory = Paths.get(appDataDirectory.toString(), getPreferencesFolderName());
    }

    private void setAppDataDirectoryAsDefault ()
    {
        if (SystemUtils.isWindows())
        {
            appDataDirectory = Paths.get(System.getenv("APPDATA"), getRootFolderName());

        }
        else
        {
            appDataDirectory = Paths.get(System.getProperty("user.home"), "." + getRootFolderName());
        }
    }

    public Path getPreferencesDirectory ()
    {
        return preferencesDirectory;
    }

    public Path getAppDataDirectory ()
    {
        return appDataDirectory;
    }

    public Path getPreferencesFile (String relativePath)
    {
        return Paths.get(getPreferencesDirectory().toString(), relativePath + ".xml");
    }

    public String getPreferencesFolderName ()
    {
        return "prefs";
    }

    public String getEnvKey ()
    {
        return "JWHEEL";
    }

    public abstract String getRootFolderName ();
}
