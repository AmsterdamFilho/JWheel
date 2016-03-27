package br.com.luvva.jwheel.view.swing.extension;

import javax.swing.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwList extends JList implements JwScrollPane.FriendlyView
{
    //<editor-fold desc="FriendlyView">
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
        return getVisibleRect().height;
    }

    @Override
    public int getVerticalUnitsCount ()
    {
        return getModel().getSize();
    }

    @Override
    public int getSelectedVerticalUnitIndex ()
    {
        return getSelectedIndex();
    }

    @Override
    public int getVerticalUnitHeight ()
    {
        return getFixedCellHeight();
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

    @Override
    public void register (JwScrollPane jwScrollPane)
    {
        addListSelectionListener(e -> jwScrollPane.updateScrollBars());
    }
    //</editor-fold>
}
