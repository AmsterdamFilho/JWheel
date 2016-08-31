package br.com.luvva.jwheel.model.beans;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.cdi.utils.NewInstance;

import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.Paths;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class PathParametersFactory implements ParameterFactory<PathParameters>
{
    private         PathParameters    pathParameters;
    private @Inject PathNamesProducer pathNamesProducer;

    @Produces
    protected PathParameters produce ()
    {
        if (pathParameters == null)
        {
            pathParameters = createParameterBeanNewInstance();
            setDefaultParameters(pathParameters);
        }
        return pathParameters;
    }

    protected PathParameters createParameterBeanNewInstance ()
    {
        return WeldContext.getInstance().getBean(PathParameters.class, new AnnotationLiteral<NewInstance>() {});
    }

    @Override
    public void setDefaultParameters (PathParameters parameters)
    {
        parameters.setAppDataDirectory(
                Paths.get(System.getProperty("user.home"), pathNamesProducer.produceAppDataFolderName()).toString());
        parameters.setParametersDirectory(Paths.get(parameters.getAppDataDirectory(), "config").toString());
        parameters.setParametersFileExtension("xml");
    }
}
