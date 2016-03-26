package br.com.luvva.jwheel.view.swing.extension;

import javax.swing.*;
import java.awt.BorderLayout;
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
        }
    }

    public void setAsFriendlyViewportView (JList jList)
    {
        setViewportView(new FriendlyViewWrapper(jList)
        {
            @Override
            public boolean shouldUpdateVertically ()
            {
                return true;
            }

            @Override
            public boolean shouldUpdateHorizontally ()
            {
                return false;
            }

            @Override
            public int getVisibleHeight ()
            {
                return jList.getVisibleRect().height;
            }

            @Override
            public int getVerticalUnitsCount ()
            {
                return jList.getModel().getSize();
            }

            @Override
            public int getSelectedVerticalUnitIndex ()
            {
                return jList.getSelectedIndex();
            }

            @Override
            public int getVerticalUnitHeight ()
            {
                return jList.getFixedCellHeight();
            }

            @Override
            public int getVisibleWidth ()
            {
                return 0;
            }

            @Override
            public int getHorizontalUnitsCount ()
            {
                return 0;
            }

            @Override
            public int getSelectedHorizontalUnitIndex ()
            {
                return 0;
            }

            @Override
            public int getHorizontalUnitWidth ()
            {
                return 0;
            }
        });
        jList.addListSelectionListener(e -> updateScrollBars());
    }

    public void setAsFriendlyViewportView (JTable jTable)
    {
        setViewportView(new FriendlyViewWrapper(jTable)
        {
            @Override
            public boolean shouldUpdateVertically ()
            {
                return true;
            }

            @Override
            public boolean shouldUpdateHorizontally ()
            {
                return false;
            }

            @Override
            public int getVisibleHeight ()
            {
                return jTable.getVisibleRect().height;
            }

            @Override
            public int getVerticalUnitsCount ()
            {
                return jTable.getModel().getRowCount();
            }

            @Override
            public int getSelectedVerticalUnitIndex ()
            {
                return jTable.getSelectedRow();
            }

            @Override
            public int getVerticalUnitHeight ()
            {
                return jTable.getRowHeight();
            }

            @Override
            public int getVisibleWidth ()
            {
                return 0;
            }

            @Override
            public int getHorizontalUnitsCount ()
            {
                return 0;
            }

            @Override
            public int getSelectedHorizontalUnitIndex ()
            {
                return 0;
            }

            @Override
            public int getHorizontalUnitWidth ()
            {
                return 0;
            }
        });
        jTable.getSelectionModel().addListSelectionListener(e -> updateScrollBars());
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

    public static abstract class FriendlyView extends JPanel
    {
        public abstract boolean shouldUpdateVertically ();

        public abstract boolean shouldUpdateHorizontally ();

        public int getVisibleHeight ()
        {
            return getVisibleRect().height;
        }

        public abstract int getVerticalUnitsCount ();

        public abstract int getSelectedVerticalUnitIndex ();

        public abstract int getVerticalUnitHeight ();

        public int getVisibleWidth ()
        {
            return getVisibleRect().width;
        }

        public abstract int getHorizontalUnitsCount ();

        public abstract int getSelectedHorizontalUnitIndex ();

        public abstract int getHorizontalUnitWidth ();
    }

    public static abstract class FriendlyViewWrapper extends FriendlyView
    {
        public FriendlyViewWrapper (Component component)
        {
            setLayout(new BorderLayout());
            add(component, BorderLayout.CENTER);
        }
    }

}
