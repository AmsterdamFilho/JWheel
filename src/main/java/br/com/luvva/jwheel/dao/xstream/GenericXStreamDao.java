package br.com.luvva.jwheel.dao.xstream;

import br.com.luvva.jwheel.java.utils.JavaLangUtils;
import br.com.luvva.jwheel.model.beans.PathParameters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Vetoed
public class GenericXStreamDao<S> implements XStreamDao<S>
{
    private @Inject Logger         logger;
    private @Inject PathParameters pathParameters;

    private PersistenceProvider<S> provider;
    private XStream                xStream;

    @PostConstruct
    protected void configure ()
    {
        xStream = createXStream();
        StorageLocation storageLocation = getStorageLocation();
        if (storageLocation == null)
        {
            throw new NullPointerException();
        }
        Class<S> typeClass = JavaLangUtils.getTypeArgumentClass(getClass());
        switch (storageLocation)
        {
            case ASK_PATH_PARAMETERS:
                provider = new FilePersistenceProvider<>(pathParameters, typeClass.getSimpleName());
                break;
            case PREFERENCES_USER_NODE:
                provider = new PreferencesPersistenceProvider<>(Preferences.userNodeForPackage(typeClass),
                        typeClass.getSimpleName());
                break;
            default:
                assert false : "Unexpected StorageLocation";
        }
    }

    protected StorageLocation getStorageLocation ()
    {
        return StorageLocation.ASK_PATH_PARAMETERS;
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
