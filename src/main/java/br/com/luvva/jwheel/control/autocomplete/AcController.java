package br.com.luvva.jwheel.control.autocomplete;

import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface AcController
{
    /**
     * Returns a list of auto complete options based on a hint
     * @param hint the hint on which the auto complete feature should base itself
     * @return the list of options
     */
    List<Object[]> getAutoCompleteOptions (String hint);

    /**
     * Notifies that an auto complete was accepted
     * @param option the chosen option
     */
    void autoCompleteAccepted (Object[] option);

    /**
     * Informs the client if the controller might take a while to show auto complete options to the hint
     * @param hint the hint to autocomplete
     * @return true if it may take a while to show auto complete options based on the hint
     */
    boolean autocompleteIsResourceConsuming (@SuppressWarnings ("UnusedParameters") String hint);

    /**
     * Registers a listener to be notified on auto complete events
     * @param listener the listener
     */
    void addListener (AcListener listener);

    /**
     * Unregisters a listener
     * @param listener the listener
     */
    void removeListener (AcListener listener);

    /**
     * Creates an String representation of the selected suggestion
     * @return the String representation
     */
    String getSelectedAsString ();
}
