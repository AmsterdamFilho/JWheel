package br.com.jwheel.javafx.formatter;

import br.com.jwheel.javafx.Binder;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class FilteredTextFormatter<T> extends Binder<TextField> implements UnaryOperator<TextFormatter.Change>
{
    private ObjectProperty<T> valueProperty = new SimpleObjectProperty<>();

    public ObjectProperty<T> valueProperty ()
    {
        return valueProperty;
    }

    @Override
    protected void unbindImpl ()
    {
        getNode().setTextFormatter(null);
        getNode().textProperty().unbindBidirectional(valueProperty);
    }

    @Override
    protected void bindImpl ()
    {
        getNode().setText("");
        getNode().setTextFormatter(new TextFormatter<>(this));
        getNode().textProperty().bindBidirectional(valueProperty, getConverter());
    }

    protected abstract StringConverter<T> getConverter ();
}
