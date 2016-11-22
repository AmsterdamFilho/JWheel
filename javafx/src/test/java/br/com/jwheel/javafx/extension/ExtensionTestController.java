package br.com.jwheel.javafx.extension;

import br.com.jwheel.javafx.adapter.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ExtensionTestController implements Initializable
{
    private @FXML TextField integerTf;
    private @FXML TextField floatTf;
    private @FXML TextField limitedLengthTf;
    private @FXML TextField dateTf;
    private @FXML TextField phoneTf;
    private @FXML TextField autoCompleteTf1;
    private @FXML TextField autoCompleteTf2;

    private IntegerAdapter integerAdapter = new IntegerAdapter();
    private FloatAdapter   floatAdapter   = new FloatAdapter(100.00f, 2);
    private LengthAdapter  lengthAdapter  = new LengthAdapter(7);

    private @Inject LocalPhoneAdapter localLocalPhoneAdapter;
    private @Inject LocalDateAdapter  localDateAdapter;

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        integerAdapter.adapt(integerTf);
        floatAdapter.adapt(floatTf);
        lengthAdapter.adapt(limitedLengthTf);
        localDateAdapter.adapt(dateTf);
        localLocalPhoneAdapter.adapt(phoneTf);
    }

    public void undoIntegerFormatting ()
    {
        integerAdapter.reset();
    }

    public void doIntegerFormatting ()
    {
        float[] limit = promptUserForNumber("Type the maximum acceptable value", "125");
        if (limit != null)
        {
            integerAdapter.reset();
            integerAdapter = new IntegerAdapter((int) limit[0]);
            integerAdapter.adapt(integerTf);
        }
    }

    public void undoFloatFormatting ()
    {
        floatAdapter.reset();
    }

    public void doFloatFormatting ()
    {
        float[] limit = promptUserForNumber("Type the maximum acceptable value", "100.00");
        if (limit != null)
        {
            float[] scale = promptUserForNumber("Type the scale", "2");
            if (scale != null)
            {
                floatAdapter.reset();
                floatAdapter = new FloatAdapter(limit[0], (int) scale[0]);
                floatAdapter.adapt(floatTf);
            }
        }
    }

    public void undoLengthFormatting ()
    {
        lengthAdapter.reset();
    }

    public void doLengthFormatting ()
    {
        final float[] limit = promptUserForNumber("Type the maximum acceptable length", "5");
        if (limit != null)
        {
            lengthAdapter.reset();
            lengthAdapter = new LengthAdapter((int) limit[0]);
            lengthAdapter.adapt(limitedLengthTf);
        }
    }

    public void undoLocalDateFormatting ()
    {
        localDateAdapter.reset();
    }

    public void doLocalDateFormatting ()
    {
        localDateAdapter.adapt(dateTf);
    }

    public void undoLocalPhoneFormatting ()
    {
        localLocalPhoneAdapter.reset();
    }

    public void doLocalPhoneFormatting ()
    {
        localLocalPhoneAdapter.adapt(phoneTf);
    }

    private float[] promptUserForNumber (String contentText, String initialValue)
    {
        String response = promptUserForString(contentText, initialValue);
        if (response == null)
        {
            return null;
        }
        return new float[]{Float.valueOf(response)};
    }

    private String promptUserForString (String contentText, String initialValue)
    {
        TextInputDialog dialog = new TextInputDialog(initialValue);
        dialog.setTitle("Decoration dialog");
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

    public void printInteger ()
    {
        System.out.println(integerAdapter.valueProperty().getValue());
    }

    public void setInteger ()
    {
        integerAdapter.valueProperty().setValue(10);
    }

    public void setIntegerNull ()
    {
        integerAdapter.valueProperty().setValue(null);
    }

    public void printFloat ()
    {
        System.out.println(floatAdapter.valueProperty().getValue());
    }

    public void setFloat ()
    {
        floatAdapter.valueProperty().setValue(5.6789f);
    }

    public void setFloatNull ()
    {
        floatAdapter.valueProperty().setValue(null);
    }

    public void printLength ()
    {
        System.out.println(lengthAdapter.valueProperty().getValue());
    }

    public void setLength ()
    {
        lengthAdapter.valueProperty().setValue("12345");
    }

    public void setLengthNull ()
    {
        lengthAdapter.valueProperty().setValue("");
    }

    public void printDate ()
    {
        System.out.println(localDateAdapter.valueProperty().getValue());
    }

    public void setDate ()
    {
        localDateAdapter.valueProperty().setValue(LocalDate.now());
    }

    public void setDateNull ()
    {
        localDateAdapter.valueProperty().setValue(null);
    }

    public void printPhone ()
    {
        System.out.println(localLocalPhoneAdapter.valueProperty().getValue());
    }

    public void setPhone ()
    {
        String phone = promptUserForString("Type only the digits of a phone", "40043535");
        if (phone != null)
        {
            localLocalPhoneAdapter.valueProperty().setValue(phone);
        }
    }

    public void setPhoneNull ()
    {
        localLocalPhoneAdapter.valueProperty().setValue(null);
    }

    public void undoAutoComplete1 ()
    {

    }

    public void doAutoComplete1 ()
    {

    }

    public void printAutoComplete1 ()
    {

    }

    public void setAutoComplete1 ()
    {

    }

    public void setAutoComplete1ToNull ()
    {

    }

    public void undoAutoComplete2 ()
    {

    }

    public void doAutoComplete2 ()
    {

    }

    public void printAutoComplete2 ()
    {

    }

    public void setAutoComplete2 ()
    {

    }

    public void setAutoComplete2ToNull ()
    {

    }
}
