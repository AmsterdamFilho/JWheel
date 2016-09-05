package br.com.luvva.jwheel.model.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ListItemsCollector
{
    // collection strategy
    public static final int COLLECT_WHEN_STARTS_WITH = 0;
    public static final int COLLECT_WHEN_CONTAINS    = 1;

    // settings
    private Validator         validator;
    private StringTransformer transformer;

    public ListItemsCollector ()
    {
        this(COLLECT_WHEN_STARTS_WITH);
    }

    public ListItemsCollector (int strategy)
    {
        validateStrategyAndSetValidator(strategy);
        this.transformer = defaultTransformer();
    }

    public ListItemsCollector (int type, StringTransformer transformer)
    {
        validateStrategyAndSetValidator(type);
        this.transformer = transformer;
    }

    private void validateStrategyAndSetValidator (int strategy)
    {
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
     *
     * @return the transformer
     */
    private StringTransformer defaultTransformer ()
    {
        return string -> {
            String notNullString = string == null ? "" : string;
            String stringWithNoAccents = StringUtils.stripAccents(notNullString);
            return stringWithNoAccents.toUpperCase();
        };
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
