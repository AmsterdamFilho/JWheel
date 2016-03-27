package br.com.luvva.jwheel.view.interfaces;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface ConnectionSettingsView
{
    void showTestSucceededMessage ();

    void showTestFailedMessage ();

    void showSaveSucceededMessageAndClose ();

    void showSaveFailedMessage ();
}
