package br.com.jwheel.javafx.control;

import br.com.jwheel.core.model.converter.LocalDateMask;

import javax.inject.Inject;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class LocalDateFilter extends MaskFilter
{
    @Inject
    public LocalDateFilter (LocalDateMask mask)
    {
        super(mask);
    }
}
