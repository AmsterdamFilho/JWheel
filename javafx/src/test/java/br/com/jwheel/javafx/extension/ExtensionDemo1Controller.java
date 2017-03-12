package br.com.jwheel.javafx.extension;

import javafx.fxml.FXML;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ExtensionDemo1Controller extends ExtensionDemoController
{
    private @FXML IntegerField     integerTf;
    private @FXML FloatField       floatTf;
    private @FXML LimitedTextField limitedLengthTf;
    private @FXML LocalDateField   localDateTf;
    private @FXML LocalPhoneField  localPhoneTf;

    public void setIntegerValue ()
    {
        float[] limit = promptUserForNumber("Type the current value", "15");
        if (limit != null)
        {
            integerTf.valueProperty().setValue((int) limit[0]);
        }
    }

    public void printInteger ()
    {
        System.out.println(integerTf.valueProperty().getValue());
    }

    public void configureInteger ()
    {
        float[] limit = promptUserForNumber("Type the maximum acceptable value", "125");
        if (limit != null)
        {
            integerTf.setLimit((int) limit[0]);
        }
    }

    public void setFloatValue ()
    {
        float[] limit = promptUserForNumber("Type the current value", "100.00");
        if (limit != null)
        {
            floatTf.valueProperty().setValue(limit[0]);
        }
    }

    public void printFloat ()
    {
        System.out.println(floatTf.valueProperty().getValue());
    }

    public void configureFloat ()
    {
        float[] limit = promptUserForNumber("Type the maximum acceptable value", "100.00");
        if (limit != null)
        {
            float[] scale = promptUserForNumber("Type the scale", "2");
            if (scale != null)
            {
                floatTf.configure(limit[0], (int) scale[0]);
            }
        }
    }

    public void setLimitedTextFieldValue ()
    {
        String value = promptUserForString("Set the current value", "Test");
        if (value != null)
        {
            limitedLengthTf.textProperty().setValue(value);
        }
    }

    public void printLimitedTextFieldValue ()
    {
        System.out.println(limitedLengthTf.textProperty().getValue());
    }

    public void configureLimitedTextField ()
    {
        float[] limit = promptUserForNumber("Type the maximum length", "7");
        if (limit != null)
        {
            limitedLengthTf.setLimit((int) limit[0]);
        }
    }

    public void setLocalDateNull ()
    {
        localDateTf.valueProperty().set(null);
    }

    public void printLocalDate ()
    {
        System.out.println(localDateTf.valueProperty().getValue());
    }

    public void setLocalPhone ()
    {
        String value = promptUserForString("Set the current value", "9777-4040");
        if (value != null)
        {
            localPhoneTf.valueProperty().setValue(value);
        }
    }

    public void printPhone ()
    {
        System.out.println(localPhoneTf.valueProperty().getValue());
    }

    public void setPhoneNull ()
    {
        localPhoneTf.valueProperty().setValue(null);
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
}
