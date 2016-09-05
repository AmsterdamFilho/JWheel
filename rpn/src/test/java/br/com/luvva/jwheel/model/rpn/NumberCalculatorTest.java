package br.com.luvva.jwheel.model.rpn;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class NumberCalculatorTest
{

    @Test
    public void testResolveRpnExpression () throws Exception
    {
        Map<String, Double> expressionsAndResultsMap = new HashMap<>();
        expressionsAndResultsMap.put("0", 0d);
        expressionsAndResultsMap.put("1 1 +", 2d);
        expressionsAndResultsMap.put("10 5 - 5 -", 0d);
        expressionsAndResultsMap.put("10 10 10 + + 3 /", 10d);
        expressionsAndResultsMap.put("5 2 ^", 25d);
        expressionsAndResultsMap.put("5 1 2 + 4 * + 3 -", 14d);
        expressionsAndResultsMap.put("5 1 2 + 4 * + 3 - 10 %", 4d);
        expressionsAndResultsMap.put("pi 10 2 ^ *", 100 * Math.PI);

        NumberCalculator numberCalculator = new NumberCalculator();
        for (Map.Entry<String, Double> expressionAndResultEntry : expressionsAndResultsMap.entrySet())
        {
            String rpnExpression = expressionAndResultEntry.getKey();
            Double expectedResult = expressionAndResultEntry.getValue();
            Double calculatedResult = numberCalculator.resolveRpnExpression(rpnExpression);
            Assert.assertEquals(expectedResult, calculatedResult);
        }
    }

    @Test
    public void testCanBeConvertedToDouble () throws Exception
    {
        Map<String, Double> validStrings = new HashMap<>();
        validStrings.put("0", 0d);
        validStrings.put(".160", .16d);
        validStrings.put("7.5", 7.5d);
        validStrings.put("0.500", .5d);
        validStrings.put("0.0", 0d);
        validStrings.put(".0", 0d);
        validStrings.put("25", 25d);
        validStrings.put("9999999999999999", 9999999999999999d);
        validStrings.put("190190.11090", 190190.11090d);
        validStrings.put("-0", -0d);
        validStrings.put("-7.5", -7.5d);
        validStrings.put("-0.500", -0.5d);
        validStrings.put("-0.0", -0d);
        validStrings.put("-.0", -0d);
        validStrings.put("-9999999999999999", -9999999999999999d);
        validStrings.put("-25", -25d);
        validStrings.put("-190190.11099", -190190.11099d);
        validStrings.forEach((s, d) -> Assert.assertEquals(Double.valueOf(s), d));

        List<String> invalidStrings = new ArrayList<>();
        invalidStrings.add("");
        invalidStrings.add("-");
        invalidStrings.add("--");
        invalidStrings.add("-.");
        invalidStrings.add("A");
        invalidStrings.add("apple");
        invalidStrings.add(".");
        invalidStrings.add("1.");
        invalidStrings.add("1.1.1");
        invalidStrings.add("1,");

        NumberCalculator calculator = new NumberCalculator();
        for (String validString : validStrings.keySet())
        {
            Assert.assertTrue(validString + " can be converted to Double!",
                    calculator.canBeConvertedToDouble(validString));
        }
        for (String invalidString : invalidStrings)
        {
            Assert.assertFalse(invalidString + " can not be converted to Double!",
                    calculator.canBeConvertedToDouble(invalidString));
        }
    }

}