package br.com.jwheel.xml.service;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleIntegerPropertyConverter implements Converter
{
    @Override
    public void marshal (Object object, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        SimpleIntegerProperty sip = (SimpleIntegerProperty) object;
        writer.startNode("value");
        writer.setValue(String.valueOf(sip.get()));
        writer.endNode();
    }

    @Override
    public SimpleIntegerProperty unmarshal (HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        reader.moveDown();
        SimpleIntegerProperty ssp = new SimpleIntegerProperty(Integer.valueOf(reader.getValue()));
        reader.moveUp();
        return ssp;
    }

    @Override
    public boolean canConvert (Class aClass)
    {
        return aClass.equals(SimpleIntegerProperty.class);
    }
}
