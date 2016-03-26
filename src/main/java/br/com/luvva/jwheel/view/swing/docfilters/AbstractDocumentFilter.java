package br.com.luvva.jwheel.view.swing.docfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class AbstractDocumentFilter extends DocumentFilter
{
    @Override
    public void replace (FilterBypass fb, int offset, int length, String text,
                         AttributeSet attrs) throws BadLocationException
    {
        if (text == null || text.isEmpty() || replaceOk(fb, offset, length, text, attrs))
        {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    public abstract boolean replaceOk (FilterBypass filterBypass, int offset, int length, String text, AttributeSet as);
}
