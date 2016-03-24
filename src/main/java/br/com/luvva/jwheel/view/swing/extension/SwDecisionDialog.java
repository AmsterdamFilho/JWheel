package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.model.beans.DecisionDialogModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SwDecisionDialog extends JwDialog
{

    private DecisionDialogModel decisionDialogModel;

    public void setDecisionDialogModel (DecisionDialogModel decisionDialogModel)
    {
        this.decisionDialogModel = decisionDialogModel;
        if (!decisionDialogModel.isSkipOptionAllowed())
        {
            setUndecorated(true);
        }

        JPanel buttonsPanel = new JPanel();
        for (String option : decisionDialogModel.getOptions())
        {
            JButton optionButton = new JButton(option);
            optionButton.addActionListener(e -> {
                decisionDialogModel.setChosenOption(option);
                dispose();
            });
            buttonsPanel.add(optionButton);
        }

        MultiLineLabel decisionDescriptionLabel = new MultiLineLabel(decisionDialogModel.getDecisionDescription());
        decisionDescriptionLabel.setFont(decisionDescriptionLabel.getFont().deriveFont(Font.BOLD));
        decisionDescriptionLabel.setVerticalTextAlignment(SwingConstants.CENTER);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(decisionDescriptionLabel, BorderLayout.CENTER);
        contentPane.add(buttonsPanel, BorderLayout.PAGE_END);

        setContentPane(new SpacedPanel(contentPane, 5, 5));

        setSize(buttonsPanel.getPreferredSize().width + 50, 150);

        setLocationRelativeTo(null);
    }

    @Override
    public void dialogRequestedToClose ()
    {
        if (decisionDialogModel.isSkipOptionAllowed())
        {
            decisionDialogModel.setDecisionAsSkipOption();
            dispose();
        }
    }
}
