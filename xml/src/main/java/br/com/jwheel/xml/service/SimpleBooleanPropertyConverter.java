package br.com.jwheel.xml.service;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleBooleanPropertyConverter implements Converter
{
    @Override
    public void marshal (Object object, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        SimpleBooleanProperty sbp = (SimpleBooleanProperty) object;
        writer.startNode("value");
        writer.setValue(sbp.get() ? "true" : "false");
        writer.endNode();
    }

    @Override
    public SimpleBooleanProperty unmarshal (HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        reader.moveDown();
        SimpleBooleanProperty ssp;
        String valueToLowerCase = reader.getValue().toLowerCase();
        if ("true".equals(valueToLowerCase))
        {
            ssp = new SimpleBooleanProperty(true);
        }
        else if ("false".equals(valueToLowerCase))
        {
            ssp = new SimpleBooleanProperty(false);
        }
        else
        {
            throw new ConversionException("Value could not be parsed to boolean: " + valueToLowerCase);
        }
        reader.moveUp();
        return ssp;
    }

    @Override
    public boolean canConvert (Class aClass)
    {
        return aClass.equals(SimpleBooleanProperty.class);
    }
}
