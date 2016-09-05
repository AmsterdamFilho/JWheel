package br.com.luvva.jwheel.model.utils;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.cdi.utils.Custom;
import br.com.luvva.jwheel.dao.xstream.GenericXStreamDao;
import br.com.luvva.jwheel.java.utils.JavaLangUtils;
import org.slf4j.Logger;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class XStreamParametersProducer
{
    public <T> T produce (GenericXStreamDao<T> dao, Logger logger, ParameterFactory<T> factory)
    {
        try
        {
            T typeFromXml = dao.find();
            if (typeFromXml != null)
            {
                return typeFromXml;
            }
        }
        catch (Exception ex)
        {
            logger.error("Could not deserialize file!", ex);
        }
        T defaultConnectionParameters = createParametersBeanNewInstance(dao);
        factory.setDefaultParameters(defaultConnectionParameters);
        try
        {
            dao.merge(defaultConnectionParameters);
        }
        catch (Exception ex)
        {
            logger.error("Could not serialize parameters to file!", ex);
        }
        return defaultConnectionParameters;
    }

    protected <T> T createParametersBeanNewInstance (GenericXStreamDao<T> xStreamDao)
    {
        return WeldContext.getInstance().getBean(JavaLangUtils.getTypeArgumentClass(xStreamDao.getClass()),
                new AnnotationLiteral<Custom>() {});
    }
}
