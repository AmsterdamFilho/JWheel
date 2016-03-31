/**
 * @(#)MultisortTableHeaderCellRenderer.java 1.0 08/20/10
 */
package br.com.luvva.jwheel.view.swing.renderers;

import br.com.luvva.jwheel.view.swing.extension.AlphaIcon;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

/**
 * An extension of <code>DefaultTableHeaderCellRenderer</code> that paints sort icons
 * on the header of each sorted column with varying opacity.
 * <p>
 * OBS: This class extended sun's DefaultTableHeaderCellRenderer. Some adaptations have been made to avoid that.
 *
 * @author Darryl
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class MultiSortTableHeaderCellRenderer implements TableCellRenderer
{

    private float             alpha;
    private TableCellRenderer helperRenderer;

    /**
     * Constructs a <code>MultiSortTableHeaderCellRenderer</code> with a default alpha of 0.5.
     *
     * @param helperRenderer a renderer to add the MultiSortIcons to
     */
    public MultiSortTableHeaderCellRenderer (TableCellRenderer helperRenderer)
    {
        this(helperRenderer, 0.5F);
    }

    /**
     * Constructs a <code>MultiSortTableHeaderCellRenderer</code> with the specified alpha.
     * A lower value represents greater contrast between icons, while a higher value can make
     * more sort icons visible.
     *
     * @param helperRenderer a renderer to add the MultiSortIcons to
     * @param alpha          the opacity, in the range 0.0F to 1.0F.  Recommended range: 0.5F to 0.7F.
     */
    public MultiSortTableHeaderCellRenderer (TableCellRenderer helperRenderer, float alpha)
    {
        this.alpha = alpha;
        this.helperRenderer = helperRenderer;
    }

    /**
     * Overridden to return an icon suitable to a sorted column, or null if the column is unsorted.
     * The icon for the primary sorted column is fully opaque, and the opacity is reduced by a
     * factor of <code>alpha</code> for each subsequent sort index.
     *
     * @param table  the <code>JTable</code>.
     * @param column the column index.
     * @return the sort icon with appropriate opacity, or null if the column is unsorted.
     */
    public Icon getIcon (JTable table, int column)
    {
        float computedAlpha = 1.0F;
        for (RowSorter.SortKey sortKey : table.getRowSorter().getSortKeys())
        {
            if (table.convertColumnIndexToView(sortKey.getColumn()) == column)
            {
                switch (sortKey.getSortOrder())
                {
                    case ASCENDING:
                        return new AlphaIcon(UIManager.getIcon("Table.ascendingSortIcon"), computedAlpha);
                    case DESCENDING:
                        return new AlphaIcon(UIManager.getIcon("Table.descendingSortIcon"), computedAlpha);
                }
            }
            computedAlpha *= alpha;
        }
        return null;
    }

    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                    int row, int column)
    {
        Component c = helperRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (c instanceof JLabel)
        {
            ((JLabel) c).setIcon(getIcon(table, column));
        }
        return c;
    }
}


