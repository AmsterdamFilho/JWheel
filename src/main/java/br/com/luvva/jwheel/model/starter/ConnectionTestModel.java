package br.com.luvva.jwheel.model.starter;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface ConnectionTestModel
{

    /**
     * Set the time after which it is considered that the test is taking too long
     * @return The time in milliseconds
     */
    int timerDelay ();

    /**
     * React to status test change event
     * @param newStatus The status of the test
     */
    void statusChanged (ConnectionTestStatus newStatus);
}
