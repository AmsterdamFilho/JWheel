package br.com.luvva.jwheel.model.beans;

import java.util.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DecisionDialogModel
{
    private List<String> options = new ArrayList<>();

    private String skipOption;
    private String decisionDescription;
    private int chosenOption = -1;

    public DecisionDialogModel (String decisionDescription, String... optionsArray)
    {
        if (optionsArray.length < 2)
        {
            throw new IllegalArgumentException("There must be at least 2 options!");
        }
        this.decisionDescription = decisionDescription;
        for (String option : optionsArray)
        {
            if (option == null || option.trim().isEmpty())
            {
                throw new IllegalArgumentException("Null or empty options won't be accepted!");
            }
            if (options.contains(option))
            {
                throw new IllegalArgumentException("Duplicated option!");
            }
            options.add(option);
        }
    }

    public DecisionDialogModel (String decisionDescription, boolean lastOptionIsSkipOption, String... optionsArray)
    {
        this(decisionDescription, optionsArray);
        if (lastOptionIsSkipOption)
        {
            skipOption = optionsArray[optionsArray.length - 1];
        }
    }

    public List<String> getOptions ()
    {
        return Collections.unmodifiableList(options);
    }

    public boolean isSkipOptionAllowed ()
    {
        return skipOption != null;
    }

    public void setAsSkipOption (String option)
    {
        if (options.contains(option))
        {
            this.chosenOption = options.indexOf(option);
        }
        else
        {
            throw new IllegalArgumentException("Option was not present in the list!");
        }
    }

    public void setDecisionAsSkipOption ()
    {
        if (!isSkipOptionAllowed())
        {
            throw new IllegalArgumentException("Skip option is not allowed!");
        }
        this.decisionDescription = skipOption;
    }

    public void setChosenOption (String chosenOption)
    {
        if (options.contains(chosenOption))
        {
            this.chosenOption = options.indexOf(chosenOption);
        }
        else
        {
            throw new IllegalArgumentException("Option was not on the list!");
        }
    }

    public int getChosenOption ()
    {
        return chosenOption;
    }

    public String getDecisionDescription ()
    {
        return decisionDescription;
    }
}
