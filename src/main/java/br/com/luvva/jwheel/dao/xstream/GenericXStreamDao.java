package br.com.luvva.jwheel.dao.xstream;

import br.com.luvva.jwheel.java.utils.JavaLangUtils;
import br.com.luvva.jwheel.model.beans.PathParameters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import org.slf4j.Logger;

import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Vetoed
public class GenericXStreamDao<E>
{

    private @Inject Logger         logger;
    private @Inject PathParameters pathParameters;

    private String fileName;

    public GenericXStreamDao ()
    {
        this.fileName = JavaLangUtils.getTypeArgumentClass(getClass()).getSimpleName();
    }

    protected GenericXStreamDao (String fileName)
    {
        this.fileName = fileName;
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
        File databaseFile = getDatabaseFile();
        if (databaseFile.exists())
        {
            return (E) xStream.fromXML(databaseFile);
        }
        return null;
    }

    public void merge (E entity) throws XStreamException, IOException
    {
        File databaseFile = getDatabaseFile();
        Files.createDirectories(databaseFile.getParentFile().toPath());
        createXStream().toXML(entity, new FileOutputStream(databaseFile));
    }

    private File getDatabaseFile ()
    {
        return pathParameters.getParametersFile(fileName);
    }

}
