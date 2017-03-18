package br.com.jwheel.jpa.service;

import br.com.jwheel.jpa.model.ConnectionParameters;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public interface ProductDatabaseParametersProvider
{
    ConnectionParameters provide ();
}
