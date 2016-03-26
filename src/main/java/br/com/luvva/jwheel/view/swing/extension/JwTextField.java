package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.view.swing.utils.SwingUtils;
import br.com.luvva.jwheel.view.swing.utils.UndoRedoDecorator;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwTextField extends JTextField
{

    public JwTextField ()
    {
        setColumns(0);
        SwingUtils.addEnterAsForwardTraversalKey(this);
        new UndoRedoDecorator().decorate(this);
    }

    public void setDocumentFilter (DocumentFilter documentFilter)
    {
        Document document = getDocument();
        if (document instanceof AbstractDocument)
        {
            AbstractDocument abstractDocument = (AbstractDocument) document;
            abstractDocument.setDocumentFilter(documentFilter);
        }
    }
}
