package br.com.jwheel.javafx.formatter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import javax.inject.Inject;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FormatterTestController implements Initializable
{
    private @FXML TextField integerTf;
    private @FXML TextField floatTf;
    private @FXML TextField limitedLengthTf;
    private @FXML TextField dateTf;
    private @FXML TextField phoneTf;
    private @FXML TextField autoCompleteTf1;
    private @FXML TextField autoCompleteTf2;

    private IntegerFormatter integerFormatter = new IntegerFormatter();
    private FloatFormatter   floatFormatter   = new FloatFormatter(100.00f, 2);
    private LengthFormatter  lengthFormatter  = new LengthFormatter(7);

    private @Inject PhoneFormatter     phoneFormatter;
    private @Inject LocalDateFormatter localDateFormatter;

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        integerFormatter.bind(integerTf);
        floatFormatter.bind(floatTf);
        lengthFormatter.bind(limitedLengthTf);
        localDateFormatter.bind(dateTf);
        phoneFormatter.bind(phoneTf);
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

    public void undoPhoneFormatting ()
    {
        phoneFormatter.unbind();
    }

    public void doPhoneFormatting ()
    {
        phoneFormatter.bind(phoneTf);
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
