package br.com.jwheel.javafx.model;

import br.com.jwheel.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class DecisionDialogModel
{
    private final List<String> otherOptions = new ArrayList<>();
    private       int          chosenOption = 0;

    private String decisionDescription;
    private String defaultOption;

    /**
     * Default constructor
     *
     * @param decisionDescription the description of the decision the user must make
     * @param defaultOption the option that is chosen if the user closes the dialog without committing to any option
     * @param otherOptions the others options
     */
    public DecisionDialogModel (String decisionDescription, String defaultOption, String... otherOptions)
    {
        if (StringUtils.isNullOrEmpty(decisionDescription) || StringUtils
                .isNullOrEmpty(defaultOption) || otherOptions == null)
        {
            throw new IllegalArgumentException("No parameter should be null!");
        }
        if (otherOptions.length < 1)
        {
            throw new IllegalArgumentException("There must be at least 1 option besides the default option!");
        }
        this.decisionDescription = decisionDescription;
        this.defaultOption = defaultOption;
        for (String option : otherOptions)
        {
            if (option == null || option.trim().isEmpty())
            {
                throw new IllegalArgumentException("Null or empty extra option won't be accepted!");
            }
            if (this.otherOptions.contains(option))
            {
                throw new IllegalArgumentException("Duplicated option!");
            }
            this.otherOptions.add(option);
        }
    }

    public List<String> getOtherOptions ()
    {
        return Collections.unmodifiableList(otherOptions);
    }

    public void setChosenOption (String chosenOption)
    {
        if (Objects.equals(chosenOption, defaultOption))
        {
            this.chosenOption = 0;
        }
        else if (otherOptions.contains(chosenOption))
        {
            this.chosenOption = otherOptions.indexOf(chosenOption) + 1;
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
