package br.com.luvva.jwheel.view.swing.utils;

import br.com.luvva.jwheel.view.swing.components.JwPasswordField;

import javax.swing.event.DocumentEvent;
import javax.swing.text.JTextComponent;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class BindingUtils
{
    public static void bind (JTextComponent textComponent, Property<String> property)
    {
        textComponent.setText(property.get());
        if (textComponent instanceof JwPasswordField)
        {
            JwPasswordField jwPasswordField = (JwPasswordField) textComponent;
            jwPasswordField.getDocument().addDocumentListener(new TextChangedListener()
            {
                @Override
                public void textChanged (DocumentEvent de)
                {
                    property.set(jwPasswordField.getStringPassword());
                }
            });
        }
        else
        {
            textComponent.getDocument().addDocumentListener(new TextChangedListener()
            {
                @Override
                public void textChanged (DocumentEvent de)
                {
                    property.set(textComponent.getText());
                }
            });
        }
    }
}
