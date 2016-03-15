package br.com.luvva.jwheel.view.swing;

import br.com.luvva.jwheel.view.interfaces.ViewConfigurator;
import br.com.luvva.jwheel.view.swing.providers.SwTextProvider;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Font;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class SwingConfigurator implements ViewConfigurator
{

    private @Inject Logger logger;
    private @Inject SwTextProvider textProvider;

    @Override
    public void configureView ()
    {
        java.awt.EventQueue.invokeLater(() -> {
            LookAndFeel laf = new NimbusLookAndFeel();
            laf.getDefaults().put("ProgressBarUI", "javax.swing.plaf.basic.BasicProgressBarUI");
            laf.getDefaults().put("ProgressBar.cycleTime", 2500);
            laf.getDefaults().put("PopupMenu.consumeEventOnClose", Boolean.FALSE);
            laf.getDefaults().put("ToolTip.font", new FontUIResource(
                    new FontUIResource("SansSerif", Font.PLAIN, 14)));
            try
            {
                UIManager.setLookAndFeel(laf);
            }
            catch (UnsupportedLookAndFeelException e)
            {
                logger.warn(textProvider.getLafErrorMessage(), e);
            }
        });
    }
}
