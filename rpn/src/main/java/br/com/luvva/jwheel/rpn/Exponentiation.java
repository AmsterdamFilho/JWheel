package br.com.luvva.jwheel.rpn;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
class Exponentiation implements NumberOperation
{
    @Override
    public double resolve (double leftOperand, double rightOperand)
    {
        return Math.pow(leftOperand, rightOperand);
    }
}
