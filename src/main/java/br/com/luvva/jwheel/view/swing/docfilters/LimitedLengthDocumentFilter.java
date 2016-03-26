package br.com.luvva.jwheel.view.swing.docfilters;

import javax.swing.text.AttributeSet;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LimitedLengthDocumentFilter extends AbstractDocumentFilter
{
    private int maximumLength;

    public LimitedLengthDocumentFilter ()
    {
        this(255);
    }

    public LimitedLengthDocumentFilter (int maximumLength)
    {
        this.maximumLength = maximumLength;
    }

    public void setMaximumLength (int maximumLength)
    {
        this.maximumLength = maximumLength;
    }

    public int getMaximumLength ()
    {
        return maximumLength;
    }

    @Override
    public boolean replaceOk (FilterBypass filterBypass, int offset, int length, String text, AttributeSet attributeSet)
    {
        int documentLength = filterBypass.getDocument().getLength();
        return (documentLength + text.length() - length) < maximumLength;
    }
}
