package br.com.jwheel.core.service.autocomplete;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class AcTableService<T> extends AutoCompleteService<T, Object[]>
{
    public abstract String[] getHeaderTitles ();
}
