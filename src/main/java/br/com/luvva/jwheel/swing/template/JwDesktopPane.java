package br.com.luvva.jwheel.swing.template;

import javax.swing.*;
import java.awt.*;

public class JwDesktopPane extends JDesktopPane
{
    private final MyDesktopManager manager       = new MyDesktopManager(this);
    private       Image            backImage     = null;
    private       int              imageStrategy = CENTRALIZE;
    private       Color            backColor     = this.getBackground();

    public static final int CENTRALIZE = 0;
    public static final int FILL       = 1;

    public JwDesktopPane ()
    {
        this((Image) null);
    }

    public JwDesktopPane (Image backImage)
    {
        this(backImage, CENTRALIZE);
    }

    public JwDesktopPane (Image backImage, int imageStrategy)
    {
        setDesktopManager(manager);
        this.backImage = backImage;
        this.imageStrategy = imageStrategy;
    }

    public JwDesktopPane (Color backColor)
    {
        setDesktopManager(manager);
        this.backColor = backColor;
    }

    @Override
    public void paintComponent (Graphics g)
    {
        if (backImage == null)
        {
            paintWithBackColor(g);
        }
        else
        {
            switch (imageStrategy)
            {
                case CENTRALIZE:
                    paintCentralizing(g);
                    break;
                case FILL:
                    paintFilling(g);
                    break;
                default:
            }
        }

    }

    private void paintCentralizing (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();

        double imgWidth = backImage.getWidth(null);
        double imgHeight = backImage.getHeight(null);
        double width = getWidth();
        double height = getHeight();
        int x = (int) ((width - imgWidth) / 2);
        int y = (int) ((height - imgHeight) / 2);
        g2d.drawImage(backImage, x, y, this);
        g2d.dispose();
    }

    private void paintFilling (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        g2d.drawImage(backImage, 0, 0, width, height, this);
        g2d.dispose();
    }

    private void paintWithBackColor (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();
        g2d.setPaint(backColor);
        g2d.fill(new Rectangle(0, 0, width, height));
        g2d.dispose();
    }

    public void setBackgroundImage (Image backImage, int imageStrategy)
    {
        validateImgStrategy(imageStrategy);
        this.backImage = backImage;
        this.imageStrategy = imageStrategy;
        repaint();
    }

    public void setBackgroundColor (Color solidColor)
    {
        this.backImage = null;
        this.backColor = solidColor;
        repaint();
    }

    public void resizeDesktop ()
    {
        manager.resizeDesktop();
    }

    private void validateImgStrategy (int imageStrategy)
    {
        switch (imageStrategy)
        {
            case CENTRALIZE:
            case FILL:
                break;
            default:
                throw new IllegalArgumentException("Image strategy invalid!");
        }
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
