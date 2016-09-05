package br.com.luvva.jwheel.model.utils;

import br.com.luvva.jwheel.model.beans.PathNamesProducer;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class JWheelPathNamesProducer implements PathNamesProducer
{
    @Override
    public String produceAppDataFolderName ()
    {
        return "jwheel";
    }

    @Override
    public String produceAppLogFileName ()
    {
        return "jwheel";
    }
}
