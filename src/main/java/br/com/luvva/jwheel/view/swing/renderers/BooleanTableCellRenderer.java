package br.com.luvva.jwheel.view.swing.renderers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class BooleanTableCellRenderer extends JCheckBox implements TableCellRenderer
{
    private Border noFocusBorder;
    private Border focusBorder;

    public BooleanTableCellRenderer ()
    {
        super();
        setOpaque(true);
        setBorderPainted(true);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent (JTable table, Object value,
                                                    boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (row >= 0)
        {
            if (row % 2 == 0)
            {
                setForeground(Color.white);
                setBackground(Color.white);
            }
            else
            {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
        }

        if (isSelected)
        {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        }

        if (hasFocus)
        {
            if (focusBorder == null)
            {
                focusBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
            }
            setBorder(focusBorder);
        }
        else
        {
            if (noFocusBorder == null)
            {
                noFocusBorder = new EmptyBorder(1, 1, 1, 1);
            }
            setBorder(noFocusBorder);
        }

        setSelected(Boolean.TRUE.equals(value));
        return this;
    }
}
