package br.com.luvva.jwheel.view.swing.utils;

import br.com.luvva.jwheel.view.interfaces.AbstractRecordViewNavigator;

import javax.swing.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JTableNavigator extends AbstractRecordViewNavigator
{

    private JTable jTable;

    public JTableNavigator (JTable jTable)
    {
        this.jTable = jTable;
    }

    @Override
    public int getSelectedRecord ()
    {
        return jTable.getSelectedRow();
    }

    @Override
    public void setSelectedRecord (int selectedRecord)
    {
        jTable.setRowSelectionInterval(selectedRecord, selectedRecord);
    }

    @Override
    public int getRecordCount ()
    {
        return jTable.getRowCount();
    }

    @Override
    public int getTotalHeight ()
    {
        return jTable.getHeight();
    }

    @Override
    public int getVisibleHeight ()
    {
        return jTable.getVisibleRect().height;
    }
}
