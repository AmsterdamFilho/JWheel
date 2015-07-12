package br.com.luvva.jwheel.dao.xstream;

import java.io.Serializable;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class Pojo implements Serializable
{
    private String property1;

    public String getProperty2 ()
    {
        return property2;
    }

    public void setProperty2 (String property2)
    {
        this.property2 = property2;
    }

    public String getProperty1 ()
    {
        return property1;
    }

    public void setProperty1 (String property1)
    {
        this.property1 = property1;
    }

    private String property2;
}
