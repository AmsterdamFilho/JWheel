package br.com.jwheel.javafx.extension;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class ExtensionDemoController
{
    String promptUserForString (String contentText, String initialValue)
    {
        TextInputDialog dialog = new TextInputDialog(initialValue);
        dialog.setTitle("Input dialog");
        dialog.setContentText(contentText);
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent())
        {
            return null;
        }
        final String[] response = new String[1];
        result.ifPresent(name -> response[0] = name);
        return response[0];
    }
}
