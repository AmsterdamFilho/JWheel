package br.com.luvva.jwheel.view.swing.starter;

import br.com.luvva.jwheel.view.swing.components.BoldLabel;
import br.com.luvva.jwheel.view.swing.components.JwDialog;
import br.com.luvva.jwheel.view.swing.components.SpacedPanel;
import br.com.luvva.jwheel.model.providers.TextProvider;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.BorderLayout;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ConnectionTestProgressDialog extends JwDialog
{

    private @Inject TextProvider textProvider;

    public ConnectionTestProgressDialog ()
    {
        setModal(false);
        setSize(400, 100);
        setUndecorated(true);
        setLocationRelativeTo(null);
    }

    @PostConstruct
    private void init ()
    {
        BoldLabel infoLabel = new BoldLabel(textProvider.databaseConnectionTestMessage());
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setIndeterminate(true);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(infoLabel, BorderLayout.CENTER);
        jPanel.add(jProgressBar, BorderLayout.PAGE_END);

        setContentPane(new SpacedPanel(jPanel, 5, 5));
    }

    @Override
    public void dialogRequestedToClose ()
    {
    }
}
