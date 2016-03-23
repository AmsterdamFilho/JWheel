package br.com.luvva.jwheel.view.swing.utils;

import br.com.luvva.jwheel.view.interfaces.AbstractRecordViewNavigator;

import javax.swing.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JListNavigator extends AbstractRecordViewNavigator
{

    private JList jList;

    public JListNavigator (JList jList)
    {
        this.jList = jList;
    }

    @Override
    public int getSelectedRecord ()
    {
        return jList.getSelectedIndex();
    }

    @Override
    public void setSelectedRecord (int selectedRecord)
    {
        jList.setSelectedIndex(selectedRecord);
    }

    @Override
    public int getRecordCount ()
    {
        return jList.getModel().getSize();
    }

    @Override
    public int getTotalHeight ()
    {
        return jList.getHeight();
    }

    @Override
    public int getVisibleHeight ()
    {
        return jList.getVisibleRect().height;
    }
}
