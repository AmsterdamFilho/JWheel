package br.com.luvva.jwheel.view.javafx.starter;

import br.com.luvva.jwheel.control.beans.ConnectionSettingsController;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.view.javafx.controls.TitledNode;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class FxConnectionSettings extends Stage
{
    private @Inject TextProvider                 textProvider;
    private @Inject ConnectionSettingsController controller;

    private TextField        txtUrl      = new TextField();
    private TextField        txtUser     = new TextField();
    private PasswordField    txtPassword = new PasswordField();
    private ComboBox<String> cmbDriver   = new ComboBox<>();

    @PostConstruct
    private void init ()
    {
        controller.setView(this);

        ConnectionParameters model = controller.getCurrentConnectionParameters();
        bindModel(model);

        GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(100));
        gridPane.getColumnConstraints().add(new ColumnConstraints(100));

        gridPane.add(new TitledNode(textProvider.connectionParametersRecordURL(), txtUrl), 0, 0, 2, 1);

        gridPane.add(new TitledNode(textProvider.connectionParametersRecordUser(), txtUser), 0, 1);
        gridPane.add(new TitledNode(textProvider.connectionParametersRecordPassword(), txtPassword), 1, 1);

        cmbDriver.setEditable(true);
        cmbDriver.setItems(FXCollections.observableArrayList(model.getDriversSuggestions()));
        gridPane.add(new TitledNode(textProvider.connectionParametersRecordDriver(), cmbDriver), 0, 2);
        Button btnTest = new Button(textProvider.connectionParametersRecordTest());
        btnTest.setOnAction(event -> controller.testConnectionParameters());
        gridPane.add(new TitledNode("", btnTest), 1, 2);

        Button btnSave = new Button(textProvider.save());
        btnSave.setOnAction(event -> controller.persistConnectionParameters());
        Button btnCancel = new Button(textProvider.cancel());
        btnCancel.setOnAction(event -> close());
        HBox buttonsBox = new HBox(btnSave, btnCancel);
        gridPane.add(new TitledNode("", buttonsBox), 0, 3, 2, 1);

        setTitle(textProvider.connectionParametersRecordTitle());
        setScene(new Scene(gridPane, 300, 300));
    }

    private void bindModel (ConnectionParameters model)
    {
        model.urlProperty().bindBidirectional(txtUrl.textProperty());
        model.userProperty().bindBidirectional(txtUser.textProperty());
        model.passwordProperty().bindBidirectional(txtPassword.textProperty());
        model.driverProperty().bindBidirectional(cmbDriver.valueProperty());
    }
}
