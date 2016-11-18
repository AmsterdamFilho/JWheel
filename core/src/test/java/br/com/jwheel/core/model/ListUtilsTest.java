package br.com.jwheel.core.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ListUtilsTest
{
    @Test
    public void findDuplicates ()
    {
        List<String> strings = new ArrayList<>();
        strings.add("unique1");
        strings.add("duplicate1");
        strings.add("duplicate2");
        strings.add("unique2");
        strings.add("duplicate2");
        strings.add("duplicate1");

        Set<String> duplicates = ListUtils.findDuplicates(strings);
        Assert.assertEquals(duplicates.size(), 2);
        Assert.assertTrue(duplicates.contains("duplicate1"));
        Assert.assertTrue(duplicates.contains("duplicate2"));

        Assert.assertTrue(ListUtils.findDuplicates(new ArrayList<>()).isEmpty());
    }
}