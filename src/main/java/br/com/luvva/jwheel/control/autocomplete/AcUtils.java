package br.com.luvva.jwheel.control.autocomplete;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AcUtils
{
    public static <T> List<Object[]> convertList (List<T> tList)
    {
        List<Object[]> result = new ArrayList<>();
        if (tList == null)
        {
        }
        else
        {
            for (T aTList : tList)
            {
                result.add(new Object[]{aTList});
            }
        }
        return result;
    }
}
