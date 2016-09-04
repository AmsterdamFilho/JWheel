package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.cdi.utils.Custom;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Custom
public class PathParameters
{
    private String appDataDirectory;
    private String parametersDirectory;
    private String parametersFileExtension;

    public String getParametersDirectory ()
    {
        return parametersDirectory;
    }

    public void setParametersDirectory (String parametersDirectory)
    {
        this.parametersDirectory = parametersDirectory;
    }

    public String getAppDataDirectory ()
    {
        return appDataDirectory;
    }

    public void setAppDataDirectory (String appDataDirectory)
    {
        this.appDataDirectory = appDataDirectory;
    }

    public String getParametersFileExtension ()
    {
        return parametersFileExtension;
    }

    public void setParametersFileExtension (String parametersFileExtension)
    {
        this.parametersFileExtension = parametersFileExtension;
    }

    public File getParametersFile (String relativePath)
    {
        return Paths.get(parametersDirectory, relativePath + "." + getParametersFileExtension()).toFile();
    }
}
