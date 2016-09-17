package br.com.luvva.jwheel.xml.model;

import br.com.luvva.jwheel.cdi.Custom;
import br.com.luvva.jwheel.cdi.WeldContext;
import br.com.luvva.jwheel.java.JavaLangUtils;
import br.com.luvva.jwheel.xml.dao.GenericXStreamDao;
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

    protected void handleMergeException (IOException e)
    {
    }

    protected boolean shouldAbortAfterFindException (Exception ex)
    {
        return false;
    }

    protected abstract void setDefaultPreferences (T parametersBean);
}