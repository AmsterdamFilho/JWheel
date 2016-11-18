package br.com.jwheel.rpn;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
class RpnInterpreter
{
    private static final char ADDITION       = '+';
    private static final char SUBTRACTION    = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION       = '/';
    private static final char EXPONENTIATION = '^';
    private static final char REMAINDER      = '%';

    NumberOperation getOperation (char symbol)
    {
        switch (symbol)
        {
            case ADDITION:
                return new Addition();
            case SUBTRACTION:
                return new Subtraction();
            case MULTIPLICATION:
                return new Multiplication();
            case DIVISION:
                return new Division();
            case EXPONENTIATION:
                return new Exponentiation();
            case REMAINDER:
                return new Remainder();
            default:
                return null;
        }
    }

}
