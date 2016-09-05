package br.com.luvva.jwheel.model.rpn;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
class Remainder implements NumberOperation
{
    @Override
    public double resolve (double leftOperand, double rightOperand)
    {
        return leftOperand % rightOperand;
    }
}
