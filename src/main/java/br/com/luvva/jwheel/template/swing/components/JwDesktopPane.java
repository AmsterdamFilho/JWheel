package br.com.luvva.jwheel.template.swing.components;

import br.com.luvva.jwheel.swing.providers.SwImageProvider;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
public class JwDesktopPane extends JDesktopPane
{
    private final MyDesktopManager manager = new MyDesktopManager(this);

    @Inject
    private SwImageProvider imageProvider;

    @Override
    public void paintComponent (Graphics g)
    {
        int width = getWidth();
        int height = getHeight();
        if (!imageProvider.decorateMainView(g, width, height, this))
        {
            super.paintComponent(g);
        }
    }

    public void resizeDesktop ()
    {
        manager.resizeDesktop();
    }

    private void setAllSize (Dimension d)
    {
        setMinimumSize(d);
        setMaximumSize(d);
        setPreferredSize(d);
    }

    private void setAllSize (int width, int height)
    {
        setAllSize(new Dimension(width, height));
    }

    /**
     * Private class used to replace the standard DesktopManager for JDesktopPane.
     * Used to provide scrollbar functionality.
     */
    private class MyDesktopManager extends DefaultDesktopManager
    {
        private final JwDesktopPane desktop;

        private MyDesktopManager (JwDesktopPane desktop)
        {
            this.desktop = desktop;
        }

        @Override
        public void endResizingFrame (JComponent f)
        {
            super.endResizingFrame(f);
            resizeDesktop();
        }

        @Override
        public void endDraggingFrame (JComponent f)
        {
            super.endDraggingFrame(f);
            resizeDesktop();
        }

        public void setNormalSize ()
        {
            JScrollPane scrollPane = getScrollPane();
            int x = 0;
            int y = 0;
            Insets scrollInsets = getScrollPaneInsets();

            if (scrollPane != null)
            {
                Dimension d = scrollPane.getVisibleRect().getSize();
                if (scrollPane.getBorder() != null)
                {
                    d.setSize(d.getWidth() - scrollInsets.left - scrollInsets.right,
                            d.getHeight() - scrollInsets.top - scrollInsets.bottom);
                }

                d.setSize(d.getWidth() - 20, d.getHeight() - 20);
                desktop.setAllSize(x, y);
                scrollPane.invalidate();
                scrollPane.validate();
            }
        }

        private Insets getScrollPaneInsets ()
        {
            JScrollPane scrollPane = getScrollPane();
            if (scrollPane == null)
            {
                return new Insets(0, 0, 0, 0);
            }
            else
            {
                return getScrollPane().getBorder().getBorderInsets(scrollPane);
            }
        }

        private JScrollPane getScrollPane ()
        {
            if (desktop.getParent() instanceof JViewport)
            {
                JViewport viewPort = (JViewport) desktop.getParent();
                if (viewPort.getParent() instanceof JScrollPane)
                {
                    return (JScrollPane) viewPort.getParent();
                }
            }
            return null;
        }

        public void resizeDesktop ()
        {
            int x = 0;
            int y = 0;
            JScrollPane scrollPane = getScrollPane();
            Insets scrollInsets = getScrollPaneInsets();

            if (scrollPane != null)
            {
                JInternalFrame allFrames[] = desktop.getAllFrames();
                for (JInternalFrame allFrame : allFrames)
                {
                    if (allFrame.getX() + allFrame.getWidth() > x)
                    {
                        x = allFrame.getX() + allFrame.getWidth();
                    }
                    if (allFrame.getY() + allFrame.getHeight() > y)
                    {
                        y = allFrame.getY() + allFrame.getHeight();
                    }
                }
                Dimension d = scrollPane.getVisibleRect().getSize();
                if (scrollPane.getBorder() != null)
                {
                    d.setSize(d.getWidth() - scrollInsets.left - scrollInsets.right,
                            d.getHeight() - scrollInsets.top - scrollInsets.bottom);
                }

                if (x <= d.getWidth())
                {
                    x = ((int) d.getWidth()) - 20;
                }
                if (y <= d.getHeight())
                {
                    y = ((int) d.getHeight()) - 20;
                }
                desktop.setAllSize(x, y);
                scrollPane.invalidate();
                scrollPane.validate();
            }

        }

    }

}
