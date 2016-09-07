package br.com.luvva.jwheel.view.javafx.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.function.DoubleConsumer;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FxUtils
{
    private FxUtils ()
    {
    }

    public static void centerOnScreen (Stage stage)
    {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double sw = screenBounds.getWidth();
        double sh = screenBounds.getHeight();

        listenToSizeInitialization(stage.widthProperty(), w -> stage.setX((sw - w) / 2));
        listenToSizeInitialization(stage.heightProperty(), h -> stage.setY((sh - h) / 2));
    }

    private static void listenToSizeInitialization (ObservableDoubleValue size, DoubleConsumer handler)
    {
        ChangeListener<Number> listener = new ChangeListener<Number>()
        {
            @Override
            public void changed (ObservableValue<? extends Number> obs, Number oldSize, Number newSize)
            {
                if (newSize.doubleValue() != Double.NaN)
                {
                    handler.accept(newSize.doubleValue());
                    size.removeListener(this);
                }
            }
        };
        size.addListener(listener);
    }
}
