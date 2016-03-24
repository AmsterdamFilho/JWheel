package br.com.luvva.jwheel.dao.xstream;

import com.thoughtworks.xstream.XStreamException;

import java.io.IOException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface XStreamDao<S>
{
    S find ();

    void merge (S serializable) throws XStreamException, IOException;
}
