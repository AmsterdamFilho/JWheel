package br.com.jwheel.javafx.extension;

import br.com.jwheel.javafx.autocomplete.AutoCompleteDecorator;
import br.com.jwheel.javafx.formatter.*;
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
    private @FXML TextField citiesTf;
    private @FXML TextField autoCompleteTf2;

    private IntegerFormatter integerFormatter = new IntegerFormatter();
    private FloatFormatter   floatFormatter   = new FloatFormatter(100.00f, 2);
    private LengthFormatter  lengthFormatter  = new LengthFormatter(7);

    private @Inject PhoneFormatter     localPhoneFormatter;
    private @Inject LocalDateFormatter localDateFormatter;

    private AutoCompleteDecorator<String> citiesDecorator = new CitiesAcDecorator();

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        integerFormatter.bind(integerTf);
        floatFormatter.bind(floatTf);
        lengthFormatter.bind(limitedLengthTf);
        localDateFormatter.bind(dateTf);
        localPhoneFormatter.bind(phoneTf);
        citiesDecorator.bind(citiesTf);
    }

    public void undoIntegerFormatting ()
    {
        integerFormatter.unbind();
    }

    public void doIntegerFormatting ()
    {
        float[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable value", "125");
        if (limit != null)
        {
            integerFormatter.unbind();
            integerFormatter = new IntegerFormatter((int) limit[0]);
            integerFormatter.bind(integerTf);
        }
    }

    public void undoFloatFormatting ()
    {
        floatFormatter.unbind();
    }

    public void doFloatFormatting ()
    {
        float[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable value", "100.00");
        if (limit != null)
        {
            float[] scale = promptUserForNumber("Decoration dialog", "Type the scale", "2");
            if (scale != null)
            {
                floatFormatter.unbind();
                floatFormatter = new FloatFormatter(limit[0], (int) scale[0]);
                floatFormatter.bind(floatTf);
            }
        }
    }

    public void undoLengthFormatting ()
    {
        lengthFormatter.unbind();
    }

    public void doLengthFormatting ()
    {
        final float[] limit = promptUserForNumber("Decoration dialog", "Type the maximum acceptable length", "5");
        if (limit != null)
        {
            lengthFormatter.unbind();
            lengthFormatter = new LengthFormatter((int) limit[0]);
            lengthFormatter.bind(limitedLengthTf);
        }
    }

    public void undoLocalDateFormatting ()
    {
        localDateFormatter.unbind();
    }

    public void doLocalDateFormatting ()
    {
        localDateFormatter.bind(dateTf);
    }

    public void undoLocalPhoneFormatting ()
    {
        localPhoneFormatter.unbind();
    }

    public void doLocalPhoneFormatting ()
    {
        localPhoneFormatter.bind(phoneTf);
    }

    private float[] promptUserForNumber (String title, String contentText, String initialValue)
    {
        String response = promptUserForString(title, contentText, initialValue);
        if (response == null)
        {
            return null;
        }
        return new float[]{Float.valueOf(response)};
    }

    private String promptUserForString (String title, String contentText, String initialValue)
    {
        TextInputDialog dialog = new TextInputDialog(initialValue);
        dialog.setTitle(title);
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
        System.out.println(integerFormatter.valueProperty().getValue());
    }

    public void setInteger ()
    {
        integerFormatter.valueProperty().setValue(10);
    }

    public void setIntegerNull ()
    {
        integerFormatter.valueProperty().setValue(null);
    }

    public void printFloat ()
    {
        System.out.println(floatFormatter.valueProperty().getValue());
    }

    public void setFloat ()
    {
        floatFormatter.valueProperty().setValue(5.6789f);
    }

    public void setFloatNull ()
    {
        floatFormatter.valueProperty().setValue(null);
    }

    public void printLength ()
    {
        System.out.println(lengthFormatter.valueProperty().getValue());
    }

    public void setLength ()
    {
        lengthFormatter.valueProperty().setValue("12345");
    }

    public void setLengthNull ()
    {
        lengthFormatter.valueProperty().setValue("");
    }

    public void printDate ()
    {
        System.out.println(localDateFormatter.valueProperty().getValue());
    }

    public void setDate ()
    {
        localDateFormatter.valueProperty().setValue(LocalDate.now());
    }

    public void setDateNull ()
    {
        localDateFormatter.valueProperty().setValue(null);
    }

    public void printPhone ()
    {
        System.out.println(localPhoneFormatter.valueProperty().getValue());
    }

    public void setPhone ()
    {
        String phone = promptUserForString("Decoration dialog", "Type only the digits of a phone", "40043535");
        if (phone != null)
        {
            localPhoneFormatter.valueProperty().setValue(phone);
        }
    }

    public void setPhoneNull ()
    {
        localPhoneFormatter.valueProperty().setValue(null);
    }

    public void undoCitiesAcDecoration ()
    {
        citiesDecorator.unbind();
    }

    public void doCitiesAcDecoration1 ()
    {

    }

    public void printCity ()
    {

    }

    public void setCity ()
    {

    }

    public void setCityToNull ()
    {

    }

    public void undoUnitsAcDecoration ()
    {
    }

    public void doUnitsAcDecoration ()
    {

    }

    public void printUnit ()
    {

    }

    public void setUnit ()
    {

    }

    public void setUnitToNull ()
    {

    }
}
