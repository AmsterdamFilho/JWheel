package br.com.luvva.jwheel.xml.model;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class PathNamesProducer
{
    public String produceAppDataFolderName ()
    {
        return "jwheel";
    }

    public String produceAppPreferencesFolderName ()
    {
        return "prefs";
    }
}
