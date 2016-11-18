package br.com.jwheel.xml.service;

import br.com.jwheel.core.cdi.WeldContext;
import br.com.jwheel.core.cdi.Custom;
import br.com.jwheel.core.java.JavaLangUtils;
import br.com.jwheel.xml.dao.GenericXStreamDao;
import com.thoughtworks.xstream.XStreamException;

import javax.enterprise.util.AnnotationLiteral;
import java.io.IOException;

/**
 * A preferences factory that get its data from XML files
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class PreferencesFactoryFromXml<T>
{
    public T produce (GenericXStreamDao<T> dao)
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

        T defaultParameter = WeldContext.getInstance().getBean(
                JavaLangUtils.getSuperclassTypeArgument(dao), new AnnotationLiteral<Custom>() {});
        setDefaultPreferences(defaultParameter);

        try
        {
            dao.merge(defaultParameter);
        }
        catch (IOException e)
        {
            handleMergeException(e);
        }

        return defaultParameter;
    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    protected void handleMergeException (IOException e)
    {
    }

    @SuppressWarnings("UnusedParameters")
    protected boolean shouldAbortAfterFindException (Exception ex)
    {
        return false;
    }

    protected abstract void setDefaultPreferences (T parametersBean);
}
