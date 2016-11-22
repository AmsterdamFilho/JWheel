package br.com.jwheel.javafx.adapter;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class FilterAdapter<T> extends ControlAdapter<TextField, T>
        implements UnaryOperator<TextFormatter.Change>
{
    @Override
    protected void resetImpl ()
    {
        getControl().setTextFormatter(null);
        getControl().textProperty().unbindBidirectional(valueProperty());
    }

    @Override
    protected void adaptImpl ()
    {
        getControl().setText("");
        getControl().setTextFormatter(new TextFormatter<>(this));
        getControl().textProperty().bindBidirectional(valueProperty(), getConverter());
    }

    protected abstract StringConverter<T> getConverter ();
}
