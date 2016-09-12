package br.com.luvva.jwheel.xml.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

import java.io.IOException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface PersistenceProvider<E>
{
    E find (XStream xStream) throws XStreamException, ClassCastException;

    void merge (XStream xStream, E entity) throws XStreamException, IOException;
}
