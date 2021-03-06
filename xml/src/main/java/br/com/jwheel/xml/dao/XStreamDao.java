package br.com.jwheel.xml.dao;

import com.thoughtworks.xstream.XStreamException;

import java.io.IOException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface XStreamDao<S>
{
    S find ();

    void merge (S object) throws XStreamException, IOException;
}
