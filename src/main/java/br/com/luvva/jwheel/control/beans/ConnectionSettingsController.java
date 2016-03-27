package br.com.luvva.jwheel.control.beans;

import br.com.luvva.jwheel.model.beans.ConnectionParameters;
import br.com.luvva.jwheel.view.interfaces.ConnectionSettingsView;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface ConnectionSettingsController
{
    ConnectionParameters getCurrentConnectionParameters ();

    void persistConnectionParameters ();

    void testConnectionParameters ();

    void setView (ConnectionSettingsView view);
}
