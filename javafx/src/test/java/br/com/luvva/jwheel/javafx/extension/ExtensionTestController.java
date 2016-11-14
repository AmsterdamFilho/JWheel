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
    private @FXML TextField integerTx;
    private @FXML TextField decimalTx;
    private @FXML TextField fixedContentLengthTx;
    private @FXML TextField autoCompleteTx1;
    private @FXML TextField autoCompleteTx2;

    private IntegerTxDecorator integerTxDecorator;

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        integerTxDecorator = new IntegerTxDecorator(integerTx);
    }

    public void undoIntegerTxDecoration ()
    {
        integerTxDecorator.undo();
    }

    public void doIntegerTxDecoration ()
    {
        final int[] upperBound = new int[1];

        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Decoration dialog");
        dialog.setContentText("Type the upper bound:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent())
        {
            return;
        }
        result.ifPresent(name -> upperBound[0] = Integer.valueOf(name));

        undoIntegerTxDecoration();
        integerTxDecorator = new IntegerTxDecorator(integerTx, upperBound[0]);
    }
}
