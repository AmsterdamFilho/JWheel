package br.com.luvva.jwheel.awt.utils;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Toolkit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class AwtUtils
{

    @Inject
    private Logger logger;

    private Path currentDirectory;
    private int menuShortcutMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    public int getMenuShortcutMask ()
    {
        return menuShortcutMask;
    }

    /**
     * Gets the JAR directory of the application. Does not work if the classes are not in JAR file
     *
     * @return the jar directory as a Path object
     * @throws Exception if, for some reason, the method failed to figure out this information
     */
    public Path getJarDirectory () throws Exception
    {
        if (currentDirectory == null)
        {
            CodeSource codeSource = AwtUtils.class.getProtectionDomain().getCodeSource();
            try
            {
                currentDirectory = Paths.get(codeSource.getLocation().toURI()).getParent();
                logger.info(currentDirectory.toString());
            }
            catch (Exception ex)
            {
                logger.error("Can't determine JAR directory!", ex);
                throw ex;
            }
        }
        return currentDirectory;
    }

}