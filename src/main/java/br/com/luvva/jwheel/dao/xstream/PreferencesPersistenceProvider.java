package br.com.luvva.jwheel.dao.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
class PreferencesPersistenceProvider<E> implements PersistenceProvider<E>
{
    private final String      relativePath;
    private final Preferences preferences;

    PreferencesPersistenceProvider (Preferences preferences, String relativePath)
    {
        this.relativePath = relativePath;
        this.preferences = preferences;
    }

    @Override
    public E find (XStream xStream) throws XStreamException, ClassCastException
    {
        String xml = preferences.get(relativePath, null);
        if (xml == null)
        {
            return null;
        }
        //noinspection unchecked
        return (E) xStream.fromXML(xml);
    }

    @Override
    public void merge (XStream xStream, E entity) throws XStreamException, IOException
    {
        preferences.put(relativePath, xStream.toXML(entity));
    }
}
