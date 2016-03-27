package br.com.luvva.jwheel.view.swing.extension;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JwTableModel extends AbstractTableModel
{

    private ArrayList<Object[]> data          = new ArrayList<>();
    private String[]            columnsTitles = new String[0];
    private Class<?>[]          columnClasses = new Class<?>[0];

    public void configure (String[] columnsTitles, Class<?>[] columnClasses)
    {
        if (columnsTitles == null || columnClasses == null)
        {
            throw new IllegalArgumentException("Neither columnsTitles nor columnsClasses can be null!");
        }
        this.columnsTitles = columnsTitles;
        this.columnClasses = columnClasses;
        fireTableStructureChanged();
    }

    public void setData (List<Object[]> newData)
    {
        if (newData == null)
        {
            setData(new ArrayList<>());
        }
        else if (newData.isEmpty() && data.isEmpty())
        {
        }
        else
        {
            data.clear();
            data.addAll(newData);
            fireTableDataChanged();
        }
    }

    public int add (Object[] row)
    {
        if (row == null || row.length == 0)
        {
            return -1;
        }
        data.add(row);
        int newRowIndex = data.size() - 1;
        fireTableRowsInserted(newRowIndex, newRowIndex);
        return newRowIndex;
    }

    public void remove (int rowIndex)
    {
        if (rowIndexOk(rowIndex))
        {
            data.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
        throw new IllegalArgumentException("Invalid row index!");
    }

    public void edit (int index, Object[] row)
    {
        if (rowIndexOk(index))
        {
            data.set(index, row);
            fireTableRowsUpdated(index, index);
        }
        throw new IllegalArgumentException("Invalid row index!");
    }

    public Object[] getRowData (int rowIndex)
    {
        if (rowIndexOk(rowIndex))
        {
            return data.get(rowIndex);
        }
        throw new IllegalArgumentException("Invalid row index!");
    }

    @Override
    public int getRowCount ()
    {
        return data.size();
    }

    @Override
    public int getColumnCount ()
    {
        return columnsTitles.length;
    }

    @Override
    public Object getValueAt (int rowIndex, int columnIndex)
    {
        if (rowIndexOk(rowIndex) && columnIndexOk(columnIndex))
        {
            return data.get(rowIndex)[columnIndex];
        }
        throw new IllegalArgumentException("Row index and/or column index invalid!");
    }

    @Override
    public String getColumnName (int columnIndex)
    {
        if (columnIndexOk(columnIndex))
        {
            return columnsTitles[columnIndex];
        }
        throw new IllegalArgumentException("Column index invalid!");
    }

    @Override
    public Class<?> getColumnClass (int columnIndex)
    {
        if (columnIndexOk(columnIndex))
        {
            return columnClasses[columnIndex];
        }
        throw new IllegalArgumentException("Column index invalid!");
    }

    private boolean rowIndexOk (int rowIndex)
    {
        return rowIndex >= 0 && rowIndex < getRowCount();
    }

    private boolean columnIndexOk (int columnIndex)
    {
        return columnIndex >= 0 && columnIndex < getColumnCount();
    }
}
