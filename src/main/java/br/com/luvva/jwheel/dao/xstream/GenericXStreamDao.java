package br.com.luvva.jwheel.dao.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class GenericXStreamDao<E extends Serializable>
{

    private @Inject Logger          logger;
    private @Inject XStreamDatabase xStreamDatabase;

    private String fileName;

    public GenericXStreamDao ()
    {
        final Type[] typeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        Class<E> firstTypeArgument = (Class<E>) typeArguments[0];
        this.fileName = firstTypeArgument.getSimpleName();
    }

    protected void setFileName (String fileName)
    {
        this.fileName = fileName;
    }

    protected XStream createXStream ()
    {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        return xStream;
    }

    public E find () throws XStreamException, ClassCastException
    {
        XStream xStream = createXStream();
        File databaseFile = xStreamDatabase.getFile(fileName);
        if (databaseFile.exists())
        {
            return (E) xStream.fromXML(databaseFile);
        }
        return null;
    }

    public void merge (E entity) throws XStreamException, Exception
    {
        File databaseFile = getDatabaseFile();
        databaseFile.getParentFile().mkdirs();
        createXStream().toXML(entity, new FileOutputStream(databaseFile));
    }

    private File getDatabaseFile ()
    {
        return xStreamDatabase.getFile(fileName);
    }

}
