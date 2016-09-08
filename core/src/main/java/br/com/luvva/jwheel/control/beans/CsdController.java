package br.com.luvva.jwheel.control.beans;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.dao.jpa.ConnectionTester;
import br.com.luvva.jwheel.dao.jpa.EntityManagerProducer;
import br.com.luvva.jwheel.dao.xstream.ConnectionParametersDao;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.view.javafx.utils.AlertProducer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class CsdController implements Initializable
{
    @FXML
    private TextField        txtUrl;
    @FXML
    private ComboBox<String> cmbDriver;
    @FXML
    private TextField        txtUser;
    @FXML
    private PasswordField    txtPassword;

    private @Inject ConnectionParameters    connectionParameters;
    private @Inject EntityManagerProducer   entityManagerProducer;
    private @Inject ConnectionParametersDao dao;
    private @Inject Logger                  logger;
    private @Inject AlertProducer           alertProducer;

    public void testConnection ()
    {
        entityManagerProducer.init(connectionParameters);
        if (WeldContext.getInstance().getBean(ConnectionTester.class).execute())
        {
            alertProducer.showConnectionTestSucceededMessage();
        }
        else
        {
            alertProducer.showConnectionTestFailedMessage();
        }
        entityManagerProducer.init();
    }

    public void saveAndExit ()
    {
        try
        {
            dao.merge(connectionParameters);
            alertProducer.showSaveSucceededMessage();
            exit();
        }
        catch (Exception e)
        {
            alertProducer.showSaveFailedMessage();
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
        model.urlProperty().bindBidirectional(txtUrl.textProperty());
        model.userProperty().bindBidirectional(txtUser.textProperty());
        model.passwordProperty().bindBidirectional(txtPassword.textProperty());
        model.driverProperty().bindBidirectional(cmbDriver.valueProperty());
    }

    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
        cmbDriver.setItems(FXCollections.observableArrayList(connectionParameters.getDriversSuggestions()));
        bindModel(connectionParameters);
    }
}
