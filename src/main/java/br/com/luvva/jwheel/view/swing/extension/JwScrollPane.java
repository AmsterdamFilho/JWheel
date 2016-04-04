package br.com.luvva.jwheel.view.swing.extension;

import javax.swing.*;
import java.awt.Component;

/**
 * A JScrollPane with a method that adjusts the scroll bars value so that the selected portion of the Viewport View can
 * become visible.
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwScrollPane extends JScrollPane
{

    private FriendlyView                    friendlyView;
    private JwScrollPaneFriendlyViewAdapter horizontalAdapter;
    private JwScrollPaneFriendlyViewAdapter verticalAdapter;

    public JwScrollPane ()
    {
        setDefaultPolicies();
    }

    public JwScrollPane (Component view)
    {
        super(view);
        setDefaultPolicies();
    }

    private void setDefaultPolicies ()
    {
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void setViewportView (Component view)
    {
        super.setViewportView(view);
        if (view instanceof FriendlyView)
        {
            friendlyView = (FriendlyView) view;
            createHorizontalAdapter();
            createVerticalAdapter();
            if (friendlyView.shouldUpdateHorizontally())
            {
                getHorizontalScrollBar().setUnitIncrement(friendlyView.getHorizontalUnitWidth());
            }
            if (friendlyView.shouldUpdateVertically())
            {
                getVerticalScrollBar().setUnitIncrement(friendlyView.getVerticalUnitHeight());
            }
            friendlyView.register(this);
        }
    }

    public void updateScrollBars ()
    {
        if (friendlyView.shouldUpdateHorizontally())
        {
            updateScrollBar(getHorizontalScrollBar(), horizontalAdapter);
        }
        if (friendlyView.shouldUpdateVertically())
        {
            updateScrollBar(getVerticalScrollBar(), verticalAdapter);
        }
    }

    @SuppressWarnings ("StatementWithEmptyBody")
    private void updateScrollBar (JScrollBar scrollBar, JwScrollPaneFriendlyViewAdapter adapter)
    {
        int selectedIndex = adapter.getSelectedUnitIndex();
        if (selectedIndex < 0)
        {
            return;
        }
        int scrollBarValue = scrollBar.getValue();
        int visibleSize = adapter.getVisibleSize();
        int viewUnitSize = adapter.getSize() / adapter.getUnitsCount();
        int selectedUnitCoordinate = selectedIndex * viewUnitSize;

        if (selectedUnitCoordinate + viewUnitSize >= scrollBarValue + visibleSize)
        {
            scrollBar.setValue(selectedUnitCoordinate - visibleSize + viewUnitSize);
        }
        else if (selectedUnitCoordinate < scrollBarValue)
        {
            scrollBar.setValue(selectedUnitCoordinate);
        }
        else
        {
            // selected portion of the view is already visible
        }
    }

    private void createHorizontalAdapter ()
    {
        horizontalAdapter = new JwScrollPaneFriendlyViewAdapter()
        {
            @Override
            public int getVisibleSize ()
            {
                return friendlyView.getVisibleWidth();
            }

            @Override
            public int getSize ()
            {
                return friendlyView.getWidth();
            }

            @Override
            public int getUnitsCount ()
            {
                return friendlyView.getHorizontalUnitsCount();
            }

            @Override
            public int getSelectedUnitIndex ()
            {
                return friendlyView.getSelectedHorizontalUnitIndex();
            }
        };
    }

    private void createVerticalAdapter ()
    {
        verticalAdapter = new JwScrollPaneFriendlyViewAdapter()
        {
            @Override
            public int getVisibleSize ()
            {
                return friendlyView.getVisibleHeight();
            }

            @Override
            public int getSize ()
            {
                return friendlyView.getHeight();
            }

            @Override
            public int getUnitsCount ()
            {
                return friendlyView.getVerticalUnitsCount();
            }

            @Override
            public int getSelectedUnitIndex ()
            {
                return friendlyView.getSelectedVerticalUnitIndex();
            }
        };
    }

    private interface JwScrollPaneFriendlyViewAdapter
    {
        int getVisibleSize ();

        int getSize ();

        int getUnitsCount ();

        int getSelectedUnitIndex ();
    }

    public interface FriendlyView
    {
        boolean shouldUpdateVertically ();

        boolean shouldUpdateHorizontally ();

        int getVisibleHeight ();

        int getVerticalUnitsCount ();

        int getSelectedVerticalUnitIndex ();

        int getVerticalUnitHeight ();

        int getHeight ();

        int getVisibleWidth ();

        int getHorizontalUnitsCount ();

        int getSelectedHorizontalUnitIndex ();

        int getHorizontalUnitWidth ();

        int getWidth ();

        void register (JwScrollPane jwScrollPane);
    }

}
