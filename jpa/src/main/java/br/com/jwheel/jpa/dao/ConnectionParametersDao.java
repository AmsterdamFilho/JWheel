package br.com.jwheel.jpa.dao;

import br.com.jwheel.jpa.model.ConnectionParameters;
import br.com.jwheel.xml.dao.GenericXStreamDao;
import br.com.jwheel.xml.service.PasswordPropertyConverter;
import br.com.jwheel.xml.service.SimpleStringPropertyConverter;
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
