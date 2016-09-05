package br.com.luvva.jwheel.view.javafx.controls;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class TitledNode extends BorderPane
{
    public TitledNode (String title, Node node)
    {
        Label label = new Label(title);
        label.getStyleClass().add("label-titled-node");

        setTop(label);
        setCenter(node);
    }
}
