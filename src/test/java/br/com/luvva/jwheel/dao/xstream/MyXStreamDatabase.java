package br.com.luvva.jwheel.dao.xstream;

import javax.enterprise.inject.Specializes;
import java.nio.file.FileSystems;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Specializes
public class MyXStreamDatabase extends JWheelXStreamDatabase
{
    @Override
    protected String getProductName ()
    {
        return "jwheel" + FileSystems.getDefault().getSeparator() + "xmlDatabase";
    }
}
