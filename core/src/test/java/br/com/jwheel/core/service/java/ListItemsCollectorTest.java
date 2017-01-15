package br.com.jwheel.core.service.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ListItemsCollectorTest
{
    @Test
    public void collect ()
    {
        List<String> someWords = new ArrayList<>();
        someWords.add("Jacob");
        someWords.add("Jesus");
        someWords.add("Fiancée");
        someWords.add("fiancee");

        ListItemsCollector defaultCollector = new ListItemsCollector();

        List<String> startingWithJ = defaultCollector.collect(someWords, "j");
        Assert.assertTrue(
                startingWithJ.size() == 2 && startingWithJ.contains("Jacob") && startingWithJ.contains("Jesus"));

        List<String> startingWithF = defaultCollector.collect(someWords, "f");
        Assert.assertTrue(
                startingWithF.size() == 2 && startingWithF.contains("Fiancée") && startingWithF.contains("fiancee"));

        ListItemsCollector customCollector = new ListItemsCollector(ListItemsCollector.COLLECT_WHEN_CONTAINS);

        List<String> containsEE = customCollector.collect(someWords, "ee");
        Assert.assertTrue(
                containsEE.size() == 2 && containsEE.contains("Fiancée") && containsEE.contains("fiancee"));
    }

}