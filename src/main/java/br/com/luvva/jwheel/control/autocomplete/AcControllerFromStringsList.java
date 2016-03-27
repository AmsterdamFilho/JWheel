package br.com.luvva.jwheel.control.autocomplete;

import br.com.luvva.jwheel.model.utils.ListItemsCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AcControllerFromStringsList extends AbstractAcController
{
    private List<String>       database;
    private ListItemsCollector listItemsCollector;

    public AcControllerFromStringsList (List<String> database)
    {
        this(database, new ListItemsCollector());
    }

    public AcControllerFromStringsList (List<String> database, ListItemsCollector listItemsCollector)
    {
        if (listItemsCollector == null)
        {
            throw new IllegalArgumentException("ListItemCollector should not be null!");
        }
        this.database = new ArrayList<>();
        if (database != null)
        {
            this.database.addAll(database);
        }
        this.listItemsCollector = listItemsCollector;
    }

    @Override
    public List<Object[]> getAutoCompleteOptions (String hint)
    {
        List<String> optionsAsStringList = listItemsCollector.collect(database, hint);
        return AcUtils.convertList(optionsAsStringList);
    }

    @Override
    public boolean autocompleteIsResourceConsuming (String hint)
    {
        return false;
    }

    @Override
    public String getSelectedAsString ()
    {
        Object[] selected = getSelected();
        if (selected == null || selected.length == 0)
        {
            return "";
        }
        return selected[0].toString();
    }
}
