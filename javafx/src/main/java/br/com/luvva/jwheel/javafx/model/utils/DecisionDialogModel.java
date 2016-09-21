package br.com.luvva.jwheel.javafx.model.utils;

import br.com.luvva.jwheel.java.StringUtils;

import java.util.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DecisionDialogModel
{
    private List<String> extraOptions = new ArrayList<>();
    private String defaultOption;

    private String decisionDescription;
    private int chosenOption = 0;

    public DecisionDialogModel (String decisionDescription, String defaultOption, String... extraOptions)
    {
        if (StringUtils.isNullOrEmpty(decisionDescription) || StringUtils
                .isNullOrEmpty(defaultOption) || extraOptions == null)
        {
            throw new IllegalArgumentException("No parameter should be null!");
        }
        if (extraOptions.length < 1)
        {
            throw new IllegalArgumentException("There must be at least 1 option besides the default option!");
        }
        this.decisionDescription = decisionDescription;
        this.defaultOption = defaultOption;
        for (String option : extraOptions)
        {
            if (option == null || option.trim().isEmpty())
            {
                throw new IllegalArgumentException("Null or empty extra option won't be accepted!");
            }
            if (this.extraOptions.contains(option))
            {
                throw new IllegalArgumentException("Duplicated option!");
            }
            this.extraOptions.add(option);
        }
    }

    public List<String> getExtraOptions ()
    {
        return Collections.unmodifiableList(extraOptions);
    }

    public void setChosenOption (String chosenOption)
    {
        if (Objects.equals(chosenOption, defaultOption))
        {
            this.chosenOption = 0;
        }
        else if (extraOptions.contains(chosenOption))
        {
            this.chosenOption = extraOptions.indexOf(chosenOption) + 1;
        }
        else
        {
            throw new IllegalArgumentException("Option was not on the list!");
        }
    }

    public String getDefaultOption ()
    {
        return defaultOption;
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
