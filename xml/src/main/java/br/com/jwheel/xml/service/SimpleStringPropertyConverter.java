package br.com.jwheel.xml.service;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SimpleStringPropertyConverter implements Converter
{
    @Override
    public void marshal (Object object, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        SimpleStringProperty ssp = (SimpleStringProperty) object;
        writer.startNode("value");
        writer.setValue(ssp.getValue() == null ? "" : ssp.getValue());
        writer.endNode();
    }

    @Override
    public SimpleStringProperty unmarshal (HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        reader.moveDown();
        SimpleStringProperty ssp = new SimpleStringProperty(reader.getValue());
        reader.moveUp();
        return ssp;
    }

    @Override
    public boolean canConvert (Class aClass)
    {
        return aClass.equals(SimpleStringProperty.class);
    }
}
