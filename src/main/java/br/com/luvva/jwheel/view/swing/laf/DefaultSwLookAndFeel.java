package br.com.luvva.jwheel.view.swing.laf;

import br.com.luvva.jwheel.awt.utils.AwtUtils;
import br.com.luvva.jwheel.java.utils.SystemUtils;

import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.DefaultEditorKit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class DefaultSwLookAndFeel implements SwLookAndFeel
{

    private final Border defaultBorder          = new BlackAndWhiteEtchedBorder();
    private final int    osxMenuShortcutKeyMask = AwtUtils.getMenuShortcutKeyMask();
    private final int    controlShortcutKeyMask = AwtUtils.getControlShortcutMask();

    @Override
    public Border defaultBorder ()
    {
        return defaultBorder;
    }

    @Override
    public LookAndFeel getLookAndFeel ()
    {
        LookAndFeel laf = new NimbusLookAndFeel();
        UIDefaults lafDefaults = laf.getDefaults();
        lafDefaults.put("ProgressBarUI", "javax.swing.plaf.basic.BasicProgressBarUI");
        lafDefaults.put("ProgressBar.cycleTime", 2500);
        lafDefaults.put("ToolTip.font", new FontUIResource(new FontUIResource("SansSerif", Font.PLAIN, 18)));
        if (SystemUtils.isOsX())
        {
            UIManager.put("Table.alternateRowColor", new Color(175, 238, 238));
        }
        if (SystemUtils.isOsX())
        {
            configureInputKeyMapForMac((InputMap) lafDefaults.get("TextPane.focusInputMap"));
            configureInputKeyMapForMac((InputMap) lafDefaults.get("FormattedTextField.focusInputMap"));
            configureInputKeyMapForMac((InputMap) lafDefaults.get("TextArea.focusInputMap"));
            configureInputKeyMapForMac((InputMap) lafDefaults.get("PasswordField.focusInputMap"));
            configureInputKeyMapForMac((InputMap) lafDefaults.get("EditorPane.focusInputMap"));
            configureInputKeyMapForMac((InputMap) lafDefaults.get("TextField.focusInputMap"));
        }
        return laf;
    }

    @SuppressWarnings ("MagicConstant")
    private void configureInputKeyMapForMac (InputMap inputMap)
    {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, osxMenuShortcutKeyMask), DefaultEditorKit.copyAction);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, osxMenuShortcutKeyMask), DefaultEditorKit.pasteAction);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, osxMenuShortcutKeyMask), DefaultEditorKit.cutAction);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, controlShortcutKeyMask), null);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, controlShortcutKeyMask), null);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, controlShortcutKeyMask), null);
    }
}
