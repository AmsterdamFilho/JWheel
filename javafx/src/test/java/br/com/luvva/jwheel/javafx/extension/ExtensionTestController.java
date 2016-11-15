package br.com.luvva.jwheel.javafx.extension;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ExtensionTestController implements Initializable
{
    private @FXML TextField integerTf;
    private @FXML TextField decimalTf;
    private @FXML TextField limitedLengthTf;
    private @FXML TextField autoCompleteTf1;
    private @FXML TextField autoCompleteTf2;

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        integerTf.setTextFormatter(new TextFormatter<>(new IntegerFilter()));
        decimalTf.setTextFormatter(new TextFormatter<>(new DecimalFilter(100, 2)));
        limitedLengthTf.setTextFormatter(new TextFormatter<>(new LengthFilter(7)));
    }

    public void undoIntegerTfDecoration ()
    {
        integerTf.setTextFormatter(null);
    }

    public void doIntegerTfDecoration ()
    {
        float[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable value", "125");
        if (limit != null)
        {
            integerTf.setText("");
            integerTf.setTextFormatter(new TextFormatter<>(new IntegerFilter((int) limit[0])));
        }
    }

    public void undoDecimalTfDecoration ()
    {
        decimalTf.setTextFormatter(null);
    }

    public void doDecimalTfDecoration ()
    {
        float[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable value", "100.00");
        if (limit != null)
        {
            float[] scale = promptUserForNumber("Decoration dialog", "Type the scale", "2");
            if (scale != null)
            {
                decimalTf.setText("");
                decimalTf.setTextFormatter(new TextFormatter<>(new DecimalFilter(limit[0], (int) scale[0])));
            }
        }
    }

    public void undoLimitedLengthTfDecoration ()
    {
        limitedLengthTf.setTextFormatter(null);
    }

    public void doLimitedLengthTfDecoration ()
    {
        final float[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable length", "5");
        if (limit != null)
        {
            limitedLengthTf.setText("");
            limitedLengthTf.setTextFormatter(new TextFormatter<>(new LengthFilter((int) limit[0])));
        }
    }

    private float[] promptUserForNumber (String title, String contentText, String initialValue)
    {
        final float[] response = new float[1];

        TextInputDialog dialog = new TextInputDialog(initialValue);
        dialog.setTitle(title);
        dialog.setContentText(contentText);
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent())
        {
            return null;
        }
        result.ifPresent(name -> response[0] = Float.valueOf(name));
        return response;
    }
}
