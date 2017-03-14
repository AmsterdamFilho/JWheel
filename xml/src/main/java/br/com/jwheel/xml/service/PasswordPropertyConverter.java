package br.com.jwheel.xml.service;

import br.com.jwheel.utils.SimpleEncoder;
import br.com.jwheel.xml.model.PasswordProperty;
import com.thoughtworks.xstream.converters.ConversionException;
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
        String value = reader.getValue();
        String decodedValue;
        try
        {
            decodedValue = SimpleEncoder.decode(value);
        }
        catch (IllegalArgumentException e)
        {
            throw new ConversionException("Password could not be decoded: " + value);
        }
        PasswordProperty ssp = new PasswordProperty(decodedValue);
        reader.moveUp();
        return ssp;
    }

    @Override
    public boolean canConvert (Class aClass)
    {
        return aClass.equals(PasswordProperty.class);
    }
}
