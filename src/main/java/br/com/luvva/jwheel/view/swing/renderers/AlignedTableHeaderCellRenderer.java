package br.com.luvva.jwheel.view.swing.renderers;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.HashMap;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AlignedTableHeaderCellRenderer implements TableCellRenderer
{
    private TableCellRenderer renderer;
    private HashMap<Integer, Integer> alignedHeadersMap = new HashMap<>();

    public AlignedTableHeaderCellRenderer (TableCellRenderer renderer)
    {
        this.renderer = renderer;
    }

    public void setAlignment (int index, int alignment)
    {
        alignedHeadersMap.put(index, alignment);
    }

    @Override
    public Component getTableCellRendererComponent (JTable jtable, Object o, boolean bln, boolean bln1, int row,
                                                    int column)
    {
        Component component = renderer.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
        if (component instanceof JLabel)
        {
            JLabel label = (JLabel) component;
            if (alignedHeadersMap.containsKey(column))
            {
                label.setHorizontalAlignment(alignedHeadersMap.get(column));
            }
            else
            {
                label.setHorizontalAlignment(SwingConstants.LEFT);
            }
        }
        return component;
    }
}
