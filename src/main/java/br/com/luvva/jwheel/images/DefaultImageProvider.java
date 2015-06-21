package br.com.luvva.jwheel.images;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class DefaultImageProvider implements ImageProvider
{

    private static final String QUESTION_MARK = "question_mark.png";

    private final String resourcesDirectory = "default/";

    private final static Logger logger = LoggerFactory.getLogger(DefaultImageProvider.class);

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
