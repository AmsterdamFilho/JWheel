package br.com.luvva.jwheel.dao.xstream;

import br.com.luvva.jwheel.model.beans.PathParameters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
class FilePersistenceProvider<E> implements PersistenceProvider<E>
{

    private final PathParameters pathParameters;
    private final String         relativePath;

    public FilePersistenceProvider (PathParameters pathParameters, String relativePath)
    {
        this.pathParameters = pathParameters;
        this.relativePath = relativePath;
    }

    @Override
    public E find (XStream xStream) throws XStreamException, ClassCastException
    {
        File databaseFile = getDatabaseFile();
        if (databaseFile.exists())
        {
            //noinspection unchecked
            return (E) xStream.fromXML(databaseFile);
        }
        return null;
    }

    @Override
    public void merge (XStream xStream, E entity) throws XStreamException, IOException
    {
        File databaseFile = getDatabaseFile();
        Files.createDirectories(databaseFile.getParentFile().toPath());
        xStream.toXML(entity, new FileOutputStream(databaseFile));
    }

    private File getDatabaseFile ()
    {
        return pathParameters.getParametersFile(relativePath);
    }
}
