package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.view.swing.renderers.AlignedTableHeaderCellRenderer;
import br.com.luvva.jwheel.view.swing.renderers.MultiSortTableHeaderCellRenderer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.Component;
import java.util.Map;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwTable extends JTable implements JwScrollPane.FriendlyView
{
    public static int DEFAULT_ROW_HEIGHT = 22;

    public JwTable (JwTableModel tblModel)
    {
        super(tblModel);
        setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        setAutoCreateRowSorter(true);
        setRowHeight(DEFAULT_ROW_HEIGHT);
    }

    public void configure (Configurator configurator)
    {
        if (configurator == null)
        {
            setTableHeader(null);
            getJwTableModel().configure(new String[]{""}, new Class<?>[]{Object.class});
        }
        else
        {
            getJwTableModel().configure(configurator.columnsTitles(), configurator.columnsClasses());
            setColumnsWidths(configurator.columnsWidths(), configurator.shouldResizeColumns());
            sortColumn(configurator.sortedColumns());
            Map<Integer, TableCellRenderer> overriddenRenderers = configurator.overriddenRenderersMap();
            if (overriddenRenderers != null)
            {
                for (Map.Entry<Integer, TableCellRenderer> entry : overriddenRenderers.entrySet())
                {
                    setRenderer(entry.getKey(), entry.getValue());
                }
            }
            setTableHeader();
        }
    }

    public void setColumnHeaderAlignment (int index, int alignment)
    {
        JTableHeader tableHeader = getTableHeader();
        if (tableHeader == null)
        {
        }
        else
        {
            TableCellRenderer tcr = tableHeader.getDefaultRenderer();
            if (tcr instanceof AlignedTableHeaderCellRenderer)
            {
                ((AlignedTableHeaderCellRenderer) tcr).setAlignment(index, alignment);
            }
        }
    }

    public void setRenderer (int index, TableCellRenderer tableCellRenderer)
    {
        TableColumn tableColumn = getColumnModel().getColumn(index);
        if (tableColumn != null)
        {
            tableColumn.setCellRenderer(tableCellRenderer);
        }
    }

    public void setColumnsWidths (int[] widths, boolean[] shouldResizeColumns)
    {
        if (widths != null && shouldResizeColumns != null && widths.length > 0 &&
                widths.length <= getColumnCount() && widths.length == shouldResizeColumns.length)
        {
            for (int i = 0; i < widths.length; i++)
            {
                TableColumn tableColumn = getColumnModel().getColumn(i);
                tableColumn.setPreferredWidth(widths[i]);
                if (!shouldResizeColumns[i])
                {
                    tableColumn.setMinWidth(widths[i]);
                    tableColumn.setMaxWidth(widths[i]);
                }
            }
        }
    }

    public void setTableHeader ()
    {
        JTableHeader defaultTableHeader = createDefaultTableHeader();
        setTableHeader(defaultTableHeader);
        defaultTableHeader.setDefaultRenderer(new AlignedTableHeaderCellRenderer(
                new MultiSortTableHeaderCellRenderer(defaultTableHeader.getDefaultRenderer())));
    }

    public void removeColumn (int columnIndex)
    {
        TableColumn tc = getColumnModel().getColumn(columnIndex);
        if (tc != null)
        {
            removeColumn(tc);
        }
    }

    public void sortColumn (int[] columnIndexes)
    {
        if (columnIndexes == null || getRowSorter() == null)
        {
            return;
        }
        for (int columnIndex : columnIndexes)
        {
            if (columnIndex >= 0 && columnIndex < getColumnCount())
            {
                getRowSorter().toggleSortOrder(columnIndex);
            }
        }
    }

    @Override
    public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int vColIndex)
    {
        Component component = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (component instanceof JLabel)
        {
            JLabel jLabel = (JLabel) component;
            jLabel.setToolTipText(jLabel.getText());
        }
        return component;
    }

    public Object[] getSelectedRowData ()
    {
        int selRowIndex = getSelectedRow();
        if (selRowIndex >= 0)
        {
            int selRowIndexToModel = convertRowIndexToModel(selRowIndex);
            return getJwTableModel().getRowData(selRowIndexToModel);
        }
        return null;
    }

    private JwTableModel getJwTableModel ()
    {
        TableModel model = getModel();
        if (model instanceof JwTableModel)
        {
            return (JwTableModel) model;
        }
        else
        {
            throw new IllegalStateException("This method should only be called if the table model is adequate!");
        }
    }

    public interface Configurator
    {
        String[] columnsTitles ();

        Class<?>[] columnsClasses ();

        Map<Integer, TableCellRenderer> overriddenRenderersMap ();

        int[] columnsWidths ();

        boolean[] shouldResizeColumns ();

        int[] sortedColumns ();
    }

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
        return getRowCount();
    }

    @Override
    public int getSelectedVerticalUnitIndex ()
    {
        return getSelectedRow();
    }

    @Override
    public int getVerticalUnitHeight ()
    {
        return getRowHeight();
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
        getSelectionModel().addListSelectionListener(e -> jwScrollPane.updateScrollBars());
    }
//</editor-fold>
}
