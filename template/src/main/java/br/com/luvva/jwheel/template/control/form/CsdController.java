package br.com.luvva.jwheel.template.control.form;

import br.com.luvva.jwheel.template.i18n.TextProvider;
import br.com.luvva.jwheel.template.service.CsdService;
import br.com.luvva.jwheel.template.view.AlertProducer;
import br.com.luvva.jwheel.jpa.ConnectionParameters;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class CsdController extends FormController
{
    private @FXML TextField        txtUrl;
    private @FXML ComboBox<String> cmbDriver;
    private @FXML TextField        txtUser;
    private @FXML PasswordField    txtPassword;

    private @Inject ConnectionParameters connectionParameters;
    private @Inject CsdService           csdService;
    private @Inject AlertProducer        alertProducer;
    private @Inject TextProvider         textProvider;

    public void testConnection ()
    {
        if (csdService.testConnection(connectionParameters))
        {
            alertProducer.showSuccessAlert(textProvider.getText(TextProvider.z_cs_testSucceededMessage));
        }
        else
        {
            alertProducer.showErrorAlert(textProvider.getText(TextProvider.z_cs_testFailedMessage));
        }
    }

    public void saveAndExit ()
    {
        if (csdService.saveOk(connectionParameters))
        {
            alertProducer.showSuccessAlert(textProvider.getText(TextProvider.z_a_saveSucceededMessage));
            exit();
        }
        else
        {
            alertProducer.showErrorAlert(textProvider.getText(TextProvider.z_a_saveFailedMessage));
        }
    }

    public void exit ()
    {
        ((Stage) txtUrl.getScene().getWindow()).close();
    }

    private void bindModel (ConnectionParameters model)
    {
        txtUrl.textProperty().bindBidirectional(model.urlProperty());
        txtUser.textProperty().bindBidirectional(model.userProperty());
        txtPassword.textProperty().bindBidirectional(model.passwordProperty());
        cmbDriver.valueProperty().bindBidirectional(model.driverProperty());
    }

    @Override
    public void start ()
    {
        cmbDriver.setItems(FXCollections.observableArrayList(connectionParameters.getDriversSuggestions()));
        bindModel(connectionParameters);
    }
}