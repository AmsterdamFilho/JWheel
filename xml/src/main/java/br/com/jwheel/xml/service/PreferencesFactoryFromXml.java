package br.com.jwheel.xml.service;

import br.com.jwheel.xml.dao.XStreamDao;
import com.thoughtworks.xstream.XStreamException;

import java.io.IOException;

/**
 * A preferences factory that get its data from XML files
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface PreferencesFactoryFromXml<T>
{
    default T produce (XStreamDao<T> dao)
    {
        T typeFromXml = null;
        try
        {
            typeFromXml = dao.find();
        }
        catch (XStreamException | ClassCastException e)
        {
            if (shouldAbortAfterFindException(e))
            {
                return null;
            }
        }

        if (typeFromXml != null)
        {
            return typeFromXml;
        }

        T defaultParameter = produceDefault();
        try
        {
            dao.merge(defaultParameter);
        }
        catch (IOException | XStreamException e)
        {
            handleMergeException(e);
        }

        return defaultParameter;
    }

    T produceDefault ();

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    default void handleMergeException (Exception e)
    {
    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    default void handleMergeException (XStreamException e)
    {
    }

    @SuppressWarnings("UnusedParameters")
    default boolean shouldAbortAfterFindException (Exception ex)
    {
        return false;
    }
}
