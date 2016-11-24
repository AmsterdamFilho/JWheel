package br.com.jwheel.javafx.extension;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class FilteredTextField<T> extends TextField implements UnaryOperator<TextFormatter.Change>
{
    private final ObjectProperty<T> valueProperty = new SimpleObjectProperty<>();

    public ObjectProperty<T> valueProperty ()
    {
        return valueProperty;
    }

    public FilteredTextField ()
    {
        setTextFormatter(new TextFormatter<>(this));
        textProperty().bindBidirectional(valueProperty(), getConverter());
    }

    protected abstract StringConverter<T> getConverter ();
}
