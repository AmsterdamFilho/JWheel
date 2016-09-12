package br.com.luvva.jwheel.javafx.view.controls;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwContentPane extends StackPane
{
    private List<EventTarget> skippedTargets = new ArrayList<>();

    public JwContentPane ()
    {
        addEventFilter(KeyEvent.KEY_PRESSED, new EnterEventHandler());
    }

    public void addTargetException (EventTarget target)
    {
        skippedTargets.add(target);
    }

    private class EnterEventHandler implements EventHandler<KeyEvent>
    {
        @Override
        public void handle (KeyEvent keyEvent)
        {
            if (eventIsNotConsumed(keyEvent) && eventIsNotCombination(keyEvent) && keyEventIsEnterKey(keyEvent))
            {
                if (!skippedTargets.contains(keyEvent.getTarget()))
                {
                    handleAccordingToTarget(keyEvent);
                }
            }
        }

        private boolean eventIsNotConsumed (KeyEvent keyEvent)
        {
            return !keyEvent.isConsumed();
        }

        private void handleAccordingToTarget (KeyEvent keyEvent)
        {
            EventTarget target = keyEvent.getTarget();
            if (target instanceof TextField || target instanceof ComboBox)
            {
                Event.fireEvent(keyEvent.getTarget(), new KeyEvent(
                        null, null, KeyEvent.KEY_PRESSED, "", "\t", KeyCode.TAB, false, false, false, false));
            }
            else if (target instanceof Button)
            {
                ((Button) target).fire();
            }
            else
            {
                return;
            }
            keyEvent.consume();
        }

        private boolean keyEventIsEnterKey (KeyEvent keyEvent)
        {
            return KeyCode.ENTER.equals(keyEvent.getCode());
        }

        private boolean eventIsNotCombination (KeyEvent keyEvent)
        {
            return !(keyEvent.isMetaDown() || keyEvent.isAltDown() || keyEvent.isControlDown() ||
                    keyEvent.isShiftDown() || keyEvent.isShortcutDown());
        }
    }
}
