package br.com.luvva.jwheel.javafx.extension;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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
    private @FXML TextField fixedContentLengthTf;
    private @FXML TextField autoCompleteTf1;
    private @FXML TextField autoCompleteTf2;

    private NumberTfDecorator integerTfDecorator;
    private NumberTfDecorator decimalTfDecorator;

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        integerTfDecorator = new NumberTfDecorator(integerTf);
        decimalTfDecorator = new NumberTfDecorator(decimalTf, 100, 2);
    }

    public void undoIntegerTfDecoration ()
    {
        integerTfDecorator.undo();
    }

    public void doIntegerTfDecoration ()
    {
        final double[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable integer", "10");
        if (limit == null)
        {
            return;
        }
        undoIntegerTfDecoration();
        integerTfDecorator = new NumberTfDecorator(integerTf, limit[0]);
    }

    public void undoDecimalTfDecoration ()
    {
        decimalTfDecorator.undo();
    }

    public void doDecimalTfDecoration ()
    {
        final double[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable double", "100.00");
        if (limit == null)
        {
            return;
        }
        final double[] scale = promptUserForNumber("Decoration dialog", "Type maximum the decimal places", "2");
        if (scale == null)
        {
            return;
        }
        undoDecimalTfDecoration();
        decimalTfDecorator = new NumberTfDecorator(decimalTf, limit[0], (int) scale[0]);
    }

    private double[] promptUserForNumber (String title, String contentText, String initialValue)
    {
        final double[] response = new double[1];

        TextInputDialog dialog = new TextInputDialog(initialValue);
        dialog.setTitle(title);
        dialog.setContentText(contentText);
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent())
        {
            return null;
        }
        result.ifPresent(name -> response[0] = Double.valueOf(name));
        return response;
    }
}
