package br.com.luvva.jwheel.dao.xstream;

import br.com.luvva.jwheel.model.utils.SimpleEncoder;
import br.com.luvva.jwheel.view.javafx.utils.PasswordProperty;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class PasswordPropertyConverter implements Converter
{
    @Override
    public void marshal (Object object, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        PasswordProperty ssp = (PasswordProperty) object;
        writer.startNode("value");
        writer.setValue(SimpleEncoder.encode(ssp.getValue()));
        writer.endNode();
    }

    @Override
    public Object unmarshal (HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        reader.moveDown();
        PasswordProperty ssp = new PasswordProperty(SimpleEncoder.decode(reader.getValue()));
        reader.moveUp();
        return ssp;
    }

    @Override
    public boolean canConvert (Class aClass)
    {
        return aClass.equals(PasswordProperty.class);
    }
}
