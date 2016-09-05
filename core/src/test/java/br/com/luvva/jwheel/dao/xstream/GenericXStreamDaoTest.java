package br.com.luvva.jwheel.dao.xstream;

import br.com.luvva.jwheel.AbstractTest;
import br.com.luvva.jwheel.WeldContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class GenericXStreamDaoTest extends AbstractTest
{
    @Test
    public void test () throws Exception
    {
        DaoPojo daoPojo = WeldContext.getInstance().getBean(DaoPojo.class);
        Pojo pojo = new Pojo();
        String property1 = "property1";
        pojo.setProperty1(property1);
        String property2 = "property2";
        pojo.setProperty2(property2);
        daoPojo.merge(pojo);

        pojo = daoPojo.find();
        Assert.assertNotNull(pojo);
        Assert.assertEquals(pojo.getProperty1(), property1);
        Assert.assertEquals(pojo.getProperty2(), property2);

        String property21 = "property21";
        pojo.setProperty2(property21);
        daoPojo.merge(pojo);

        pojo = daoPojo.find();
        Assert.assertNotNull(pojo);
        Assert.assertEquals(pojo.getProperty2(), property21);
    }
}
