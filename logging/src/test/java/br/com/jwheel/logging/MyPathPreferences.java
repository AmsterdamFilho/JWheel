package br.com.jwheel.logging;

import br.com.jwheel.xml.model.PathPreferences;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MyPathPreferences extends PathPreferences
{
    @Override
    public String getRootFolderName ()
    {
        return "test-jwheel-logging";
    }
}
