package br.com.luvva.jwheel;

import br.com.luvva.jwheel.model.beans.LocalParameters;
import br.com.luvva.jwheel.model.beans.LocalParametersFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import java.nio.file.FileSystems;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
public class MyLocalParameterFactory extends LocalParametersFactory
{

    @Override
    @Specializes
    @Produces
    protected LocalParameters getLocalParameters ()
    {
        return super.getLocalParameters();
    }

    @Override
    protected void setDefaultPathParameters (LocalParameters lp)
    {
        String separator = FileSystems.getDefault().getSeparator();
        lp.setLogFilePath("tests" + separator + "jwheel.log");
        lp.setAppDataDirectory("tests");
    }
}
