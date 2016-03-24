package br.com.luvva.jwheel.view.swing.utils;

import br.com.luvva.jwheel.view.swing.extension.JwPasswordField;

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
        textComponent.getDocument().addDocumentListener(new TextChangedListener()
        {
            @Override
            public void textChanged (DocumentEvent de)
            {
                property.set(textComponent.getText());
            }
        });
    }

    public static void bind (JwPasswordField jwPasswordField, Property<String> property)
    {
        jwPasswordField.setText(property.get());
        jwPasswordField.getDocument().addDocumentListener(new TextChangedListener()
        {
            @Override
            public void textChanged (DocumentEvent de)
            {
                property.set(jwPasswordField.getStringPassword());
            }
        });
    }

    public interface Property<T>
    {
        T get ();

        void set (T t);
    }
}
