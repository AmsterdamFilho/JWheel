package br.com.luvva.jwheel.view.swing.extension;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class IndeterminateProgressBarDialog extends JwDialog
{

    private JLabel infoLabel;

    public IndeterminateProgressBarDialog ()
    {
        setModal(false);
        setSize(400, 80);
        setUndecorated(true);
        setLocationRelativeTo(null);
    }

    @PostConstruct
    private void init ()
    {
        infoLabel = new BoldLabel("", SwingConstants.CENTER);
        infoLabel.setForeground(Color.black);
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setIndeterminate(true);
        jProgressBar.setBorder(new LineBorder(Color.black));

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(infoLabel, BorderLayout.CENTER);
        jPanel.add(new SpacedPanel(jProgressBar, 0, 5, 0, 0), BorderLayout.PAGE_END);

        JPanel contentPanel = new SpacedPanel(jPanel, 5, 5);
        contentPanel.setBorder(new LineBorder(Color.black));
        setContentPane(contentPanel);
    }

    public void setInfoLabelText (String text)
    {
        infoLabel.setText(text);
    }

    @Override
    public void dialogRequestedToClose ()
    {
    }
}
