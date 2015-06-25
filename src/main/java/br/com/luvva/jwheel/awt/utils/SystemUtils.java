package br.com.luvva.jwheel.awt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.awt.Toolkit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@Singleton
public class SystemUtils
{

    // can not be injected, or else it would create a circular reference problem
    private Logger logger = LoggerFactory.getLogger(SystemUtils.class);

    private Path currentDirectory;
    private int menuShortcutMask = -1;

    public int getMenuShortcutMask ()
    {
        if (-1 == menuShortcutMask)
        {
            menuShortcutMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        }
        return menuShortcutMask;
    }

    public Path getJarDirectory () throws Exception
    {
        if (currentDirectory == null)
        {
            CodeSource codeSource = SystemUtils.class.getProtectionDomain().getCodeSource();
            try
            {
                currentDirectory = Paths.get(codeSource.getLocation().toURI()).getParent();
                logger.info(currentDirectory.toString());
            }
            catch (Exception ex)
            {
                logger.error("Can't determine JAR or Class root directory!", ex);
                throw ex;
            }
        }
        return currentDirectory;
    }

}