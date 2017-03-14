package br.com.jwheel.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ListUtils
{
    public static <T> Set<T> findDuplicates (List<T> list)
    {
        if (list == null)
        {
            return new HashSet<>();
        }
        return list.stream().filter(i -> Collections.frequency(list, i) > 1).collect(Collectors.toSet());
    }
}
