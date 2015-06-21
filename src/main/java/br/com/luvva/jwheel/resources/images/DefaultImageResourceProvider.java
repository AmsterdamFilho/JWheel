package br.com.luvva.jwheel.resources.images;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class DefaultImageResourceProvider implements ImageResourceProvider
{

    private static final String QUESTION_MARK = "question_mark.png";

    private final String resourcesDirectory = "resources/default/";

    private final static Logger logger = LoggerFactory.getLogger(DefaultImageResourceProvider.class);

    @Override
    public Icon getQuestionIcon ()
    {
        return getIcon(QUESTION_MARK);
    }

    private Icon getIcon (String fileName)
    {
        try
        {
            return new ImageIcon(getClass().getResource(resourcesDirectory + "images/" + fileName));
        }
        catch (Exception ex)
        {
            logger.error("Could not create icon for image: " + fileName, ex);
            return null;
        }
    }

}
