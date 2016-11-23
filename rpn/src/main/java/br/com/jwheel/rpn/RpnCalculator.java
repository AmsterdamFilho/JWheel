package br.com.jwheel.rpn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * An RPN calculator. It is intended to be used with simple calculations, with few operations. Otherwise, the result
 * precision might be a problem, in which case an implementation that uses {@link java.math.BigDecimal} would be a much
 * better choice.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class RpnCalculator
{
    private final RpnInterpreter rpnInterpreter = new RpnInterpreter();

    public double resolveRpnExpression (String rpnExpression) throws IllegalArgumentException
    {
        Deque<Double> resultDeque = new ArrayDeque<>();
        for (String expressionItem : rpnExpression.split(" "))
        {
            if (CalculatorSymbols.E.equalsIgnoreCase(expressionItem))
            {
                resultDeque.push(Math.E);
            }
            else if (CalculatorSymbols.PI.equalsIgnoreCase(expressionItem))
            {
                resultDeque.push(Math.PI);
            }
            else if (canBeConvertedToDouble(expressionItem))
            {
                resultDeque.push(Double.valueOf(expressionItem));
            }
            else
            {
                NumberOperation numberOperation = rpnInterpreter.getOperation(expressionItem.charAt(0));
                if (numberOperation == null)
                {
                    throw new IllegalArgumentException("Could not parse expression item: " + expressionItem);
                }
                if (resultDeque.size() < 2)
                {
                    throw new IllegalArgumentException("The expression contains an operation without operands!");
                }
                double rightOperand = resultDeque.pop();
                double leftOperand = resultDeque.pop();
                resultDeque.push(numberOperation.resolve(leftOperand, rightOperand));
            }
        }
        if (resultDeque.isEmpty())
        {
            throw new IllegalArgumentException("Could not understand the expression!");
        }
        return resultDeque.pop();
    }

    public boolean canBeConvertedToDouble (String expression)
    {
        return !(expression.isEmpty() || expression.equals("-")) && expression.matches("-?[0-9]*(\\.[0-9]+)?");
    }
}
