package br.com.luvva.jwheel.xml.dao;

import br.com.luvva.jwheel.java.JavaLangUtils;
import br.com.luvva.jwheel.xml.model.PathPreferences;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class GenericXStreamDao<S> implements XStreamDao<S>
{
    private @Inject PathPreferences pathPreferences;

    private PreferencesDao<S> provider;
    private XStream           xStream;

    @PostConstruct
    protected void configure ()
    {
        xStream = createXStream();
        Class<S> typeClass = JavaLangUtils.getSuperclassTypeArgument(this);
        provider = new PreferencesDaoFromFile<>(pathPreferences, typeClass.getSimpleName());
    }

    protected XStream createXStream ()
    {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        return xStream;
    }

    protected XStream getXStream ()
    {
        return xStream;
    }

    @Override
    public S find () throws XStreamException, ClassCastException
    {
        return provider.find(getXStream());
    }

    @Override
    public void merge (S serializable) throws XStreamException, IOException
    {
        provider.merge(getXStream(), serializable);
    }
}
