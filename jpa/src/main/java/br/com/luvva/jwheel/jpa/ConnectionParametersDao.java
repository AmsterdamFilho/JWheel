package br.com.luvva.jwheel.jpa;

import br.com.luvva.jwheel.xml.dao.GenericXStreamDao;
import br.com.luvva.jwheel.xml.service.PasswordPropertyConverter;
import br.com.luvva.jwheel.xml.service.SimpleStringPropertyConverter;
import com.thoughtworks.xstream.XStream;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionParametersDao extends GenericXStreamDao<ConnectionParameters>
{
    @Override
    protected XStream createXStream ()
    {
        XStream xStream = super.createXStream();
        xStream.registerConverter(new SimpleStringPropertyConverter());
        xStream.registerConverter(new PasswordPropertyConverter());
        return xStream;
    }
}
