package br.com.jwheel.template.control;

import br.com.jwheel.javafx.laf.DialogProducer;
import br.com.jwheel.jpa.ConnectionParameters;
import br.com.jwheel.template.MyResourceProvider;
import br.com.jwheel.template.service.CsdService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.inject.Inject;

import static br.com.jwheel.template.MyResourceProvider.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class CsdController extends RecordPaneController
{
    private @FXML TextField        txtUrl;
    private @FXML ComboBox<String> cmbDriver;
    private @FXML TextField        txtUser;
    private @FXML PasswordField    txtPassword;

    private @Inject ConnectionParameters connectionParameters;
    private @Inject CsdService           csdService;
    private @Inject DialogProducer       dialogProducer;
    private @Inject MyResourceProvider   resourceProvider;

    public void testConnection ()
    {
        if (csdService.testConnection(connectionParameters))
        {
            dialogProducer.showSuccessAlert(resourceProvider.getI18nProperty(z_cs_testSucceededMessage));
        }
        else
        {
            dialogProducer.showErrorAlert(resourceProvider.getI18nProperty(z_cs_testFailedMessage));
        }
    }

    public void saveAndExit ()
    {
        if (csdService.saveOk(connectionParameters))
        {
            dialogProducer.showSuccessAlert(resourceProvider.getI18nProperty(z_a_saveSucceededMessage));
            exit();
        }
        else
        {
            dialogProducer.showErrorAlert(resourceProvider.getI18nProperty(z_a_saveFailedMessage));
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
