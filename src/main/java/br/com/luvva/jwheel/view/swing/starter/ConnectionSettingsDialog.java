package br.com.luvva.jwheel.view.swing.starter;

import br.com.luvva.jwheel.WeldContext;
import br.com.luvva.jwheel.control.autocomplete.AcControllerFromStringsList;
import br.com.luvva.jwheel.control.beans.ConnectionSettingsController;
import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.model.utils.ListItemsCollector;
import br.com.luvva.jwheel.view.interfaces.ConnectionSettingsView;
import br.com.luvva.jwheel.view.swing.extension.*;
import br.com.luvva.jwheel.view.swing.utils.BindingUtils;
import br.com.luvva.jwheel.view.swing.utils.InvokeAndWaitHandler;
import br.com.luvva.jwheel.view.swing.utils.SwingUtils;
import br.com.luvva.jwheel.view.swing.utils.WaitingCursorAction;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionSettingsDialog extends JwDialog implements ConnectionSettingsView
{

    private @Inject SwingUtils                   swingUtils;
    private @Inject ConnectionSettingsController controller;
    private @Inject TextProvider                 textProvider;
    private @Inject OkCancelButtonPanel          buttonsPanel;
    private @Inject Logger                       logger;
    private @Inject InvokeAndWaitHandler         invokeAndWaitHandler;
    private @Inject AcDecorator                  driverDecorator;
    private         ConnectionParameters         model;

    @PostConstruct
    private void init ()
    {
        model = controller.getCurrentConnectionParameters();
        controller.setView(this);

        GridBagLayoutPanel contentPanel = new GridBagLayoutPanel();
        GridBagConstraints c = contentPanel.getConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        JwTextField txtUrl = WeldContext.getInstance().getBean(JwTextField.class);
        BindingUtils.bind(txtUrl, new BindingUtils.Property<String>()
        {
            @Override
            public String get ()
            {
                return model.getDatabaseUrl();
            }

            @Override
            public void set (String s)
            {
                model.setDatabaseUrl(s);
            }
        });
        contentPanel.add(txtUrl, textProvider.connectionParametersRecordURL());

        c.gridwidth = 1;
        c.gridy = 1;
        c.weightx = 1;
        JwTextField txtUser = WeldContext.getInstance().getBean(JwTextField.class);
        BindingUtils.bind(txtUser, new BindingUtils.Property<String>()
        {
            @Override
            public String get ()
            {
                return model.getDatabaseUser();
            }

            @Override
            public void set (String s)
            {
                model.setDatabaseUser(s);
            }
        });
        contentPanel.add(txtUser, textProvider.connectionParametersRecordUser());
        c.gridx = 1;
        c.weightx = 0;
        JwPasswordField txtPassword = WeldContext.getInstance().getBean(JwPasswordField.class);
        BindingUtils.bind(txtPassword, new BindingUtils.Property<String>()
        {
            @Override
            public String get ()
            {
                return model.getDatabasePassword();
            }

            @Override
            public void set (String s)
            {
                model.setDatabasePassword(s);
            }
        });
        contentPanel.add(txtPassword, textProvider.connectionParametersRecordPassword());

        c.gridx = 0;
        c.gridy = 2;
        JwTextField txtDriver = WeldContext.getInstance().getBean(JwTextField.class);
        driverDecorator.setController(new AcControllerFromStringsList(
                model.getDriversSuggestions(),
                new ListItemsCollector(ListItemsCollector.COLLECT_WHEN_CONTAINS)));
        driverDecorator.setSelectFirstWhenPopupShows(false);
        driverDecorator.decorate(txtDriver);
        BindingUtils.bind(txtDriver, new BindingUtils.Property<String>()
        {
            @Override
            public String get ()
            {
                return model.getDatabaseDriver();
            }

            @Override
            public void set (String s)
            {
                model.setDatabaseDriver(s);
            }
        });
        contentPanel.add(txtDriver, textProvider.connectionParametersRecordDriver());
        c.gridx = 1;
        JButton btnTest = new JButton(textProvider.connectionParametersRecordTest());
        btnTest.addActionListener(
                new WaitingCursorAction(() -> controller.testConnectionParameters(), 1000, this));
        contentPanel.add(btnTest, "");

        buttonsPanel.configure(
                new WaitingCursorAction(() -> controller.persistConnectionParameters(), 1000, this),
                e -> dispose(), OkCancelButtonPanel.SAVE);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 10;
        c.ipady = 10;
        c.insets.top = 8;
        contentPanel.add(buttonsPanel, c);

        setContentPane(new SpacedPanel(contentPanel, 5, 5));

        setSize(475, 230);

        setTitle(textProvider.connectionParametersRecordTitle());

        setLocationRelativeTo(null);
    }

    @Override
    public void dialogRequestedToClose ()
    {
        dispose();
    }

    @Override
    public void showTestSucceededMessage ()
    {
        EventQueue.invokeLater(
                () -> swingUtils.showSuccessMessage(this, textProvider.connectionParametersRecordTestSuccessful()));
    }

    @Override
    public void showTestFailedMessage ()
    {
        EventQueue.invokeLater(() -> swingUtils
                .showErrorMessage(ConnectionSettingsDialog.this, textProvider.connectionParametersRecordTestFailed()));
    }

    @Override
    public void showSaveSucceededMessageAndClose ()
    {
        EventQueue.invokeLater(() -> {
            swingUtils.showSuccessMessage(this, textProvider.savedSuccessfully());
            dispose();
        });
    }

    @Override
    public void showSaveFailedMessage ()
    {
        EventQueue.invokeLater(() -> swingUtils.showErrorMessage(this, textProvider.failedToSave()));
    }
}
