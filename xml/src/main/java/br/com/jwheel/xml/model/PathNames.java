package br.com.jwheel.xml.model;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class PathNames
{
    public String getAppDataFolderName ()
    {
        return "jwheel";
    }

    public String getAppPreferencesFolderName ()
    {
        return "prefs";
    }
}
