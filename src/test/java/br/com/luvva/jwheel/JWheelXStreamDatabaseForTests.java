package br.com.luvva.jwheel;

import br.com.luvva.jwheel.dao.xstream.JWheelXStreamDatabase;

import javax.enterprise.inject.Specializes;
import java.nio.file.FileSystems;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
public class JWheelXStreamDatabaseForTests extends JWheelXStreamDatabase
{
    @Override
    protected String getDefaultDatabaseDirectory ()
    {
        return "tests" + FileSystems.getDefault().getSeparator() + "preferences";
    }
}
