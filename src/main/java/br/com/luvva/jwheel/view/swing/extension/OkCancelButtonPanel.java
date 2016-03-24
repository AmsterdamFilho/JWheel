package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.model.providers.TextProvider;
import br.com.luvva.jwheel.view.swing.providers.SwImageProvider;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class OkCancelButtonPanel extends JPanel
{

    private JButton btnOk     = new JButton();
    private JButton btnCancel = new JButton();

    public static final int SAVE    = 0;
    public static final int CONFIRM = 1;

    private @Inject SwImageProvider imageProvider;
    private @Inject TextProvider    textProvider;

    private boolean configured = false;

    public void configure (ActionListener actionOk, ActionListener actionCancel, int buttonOkType)
    {

        if (configured)
        {
            throw new IllegalStateException("Can't be configured twice!!");
        }

        switch (buttonOkType)
        {
            case SAVE:
                btnOk.setText(textProvider.save());
                btnOk.setIcon(imageProvider.saveIcon());
                break;
            case CONFIRM:
                btnOk.setText(textProvider.confirm());
                btnOk.setIcon(imageProvider.confirmIcon());
                break;
            default:
                throw new IllegalArgumentException("Button Ok type invalid!");
        }
        btnOk.addActionListener(actionOk);
        btnCancel.addActionListener(actionCancel);
        btnCancel.setText(textProvider.cancel());
        btnCancel.setIcon(imageProvider.cancelIcon());

        setLayout(new GridLayout(1, 2, 5, 0));
        add(btnOk);
        add(btnCancel);
        configured = true;
    }

}
