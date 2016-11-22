package br.com.jwheel.core.model;

import br.com.jwheel.core.java.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for collection items from a List according to specific rules.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ListItemsCollector
{
    // collection strategy
    public static final int COLLECT_WHEN_STARTS_WITH = 0;
    public static final int COLLECT_WHEN_CONTAINS    = 1;

    // settings
    private final Validator         validator;
    private final StringTransformer transformer;

    public ListItemsCollector ()
    {
        this(COLLECT_WHEN_STARTS_WITH);
    }

    public ListItemsCollector (int strategy)
    {
        this(strategy, new DefaultStringTransformer());
    }

    public ListItemsCollector (int strategy, StringTransformer transformer)
    {
        // validate strategy and set validator
        switch (strategy)
        {
            case COLLECT_WHEN_STARTS_WITH:
                validator = (item, transformedFilter) -> transformer.transform(item).startsWith(transformedFilter);
                break;
            case COLLECT_WHEN_CONTAINS:
                validator = (item, transformedFilter) -> transformer.transform(item).contains(transformedFilter);
                break;
            default:
                throw new IllegalArgumentException("Strategy invalid!");
        }
        this.transformer = transformer;
    }

    public List<String> collect (List<String> list, String filter)
    {
        List<String> response = new ArrayList<>();
        if (!(list == null || list.isEmpty()))
        {
            String transformedFilter = transformer.transform(filter);
            response.addAll(list.stream().filter(string -> validator.validate(string, transformedFilter))
                    .collect(Collectors.toList()));
        }
        return response;
    }

    /**
     * Returns a transformer that converts to upper case, strip the accents and treat null as empty string
     */
    private static class DefaultStringTransformer implements StringTransformer
    {
        @Override
        public String transform (String string)
        {
            String notNullString = string == null ? "" : string;
            String stringWithNoAccents = StringUtils.stripAccents(notNullString);
            return stringWithNoAccents.toUpperCase();
        }
    }

    /**
     * Performs an operation on a String so that it can be compared to another string that is not equal but is close
     * enough
     */
    public interface StringTransformer
    {
        String transform (String string);
    }

    private interface Validator
    {
        boolean validate (String item, String transformedFilter);
    }
}
