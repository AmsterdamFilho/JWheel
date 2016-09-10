package br.com.luvva.jwheel.control.beans;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.control.DefaultController;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.dao.jpa.EntityManagerProducer;
import br.com.luvva.jwheel.dao.xstream.ConnectionParametersDao;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.model.i18n.TextProvider;
import br.com.luvva.jwheel.view.javafx.utils.AlertProducer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class CsdController extends DefaultController
{
    private @FXML TextField        txtUrl;
    private @FXML ComboBox<String> cmbDriver;
    private @FXML TextField        txtUser;
    private @FXML PasswordField    txtPassword;

    private @Inject ConnectionParameters    connectionParameters;
    private @Inject EntityManagerProducer   entityManagerProducer;
    private @Inject ConnectionParametersDao dao;
    private @Inject Logger                  logger;
    private @Inject AlertProducer           alertProducer;
    private @Inject TextProvider            textProvider;

    public void testConnection ()
    {
        entityManagerProducer.init(connectionParameters);
        if (WeldContext.getInstance().getBean(ConnectionTester.class).execute())
        {
            alertProducer.showSuccessAlert(textProvider.getText(TextProvider.cs_testSucceeded));
        }
        else
        {
            alertProducer.showErrorAlert(textProvider.getText(TextProvider.cs_testFailed));
        }
        entityManagerProducer.init();
    }

    public void saveAndExit ()
    {
        try
        {
            dao.merge(connectionParameters);
            alertProducer.showSuccessAlert(textProvider.getText(TextProvider.g_saveSucceeded));
            exit();
        }
        catch (Exception e)
        {
            alertProducer.showErrorAlert(textProvider.getText(TextProvider.g_saveFailed));
            logger.error("Could not persist Connection Parameters", e);
        }
        entityManagerProducer.init();
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
