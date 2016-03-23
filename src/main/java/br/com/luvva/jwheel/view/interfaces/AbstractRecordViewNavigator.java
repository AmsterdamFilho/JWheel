package br.com.luvva.jwheel.view.interfaces;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public abstract class AbstractRecordViewNavigator implements RecordViewNavigator
{
    @Override
    public void first ()
    {
        if (validate())
        {
            int firstLineIndex = 0;
            setSelectedRecord(firstLineIndex);
        }
    }

    @Override
    public void previousPage ()
    {
        alterPage(-1);
    }

    @Override
    public void previous ()
    {
        if (validate())
        {
            int firstLineIndex = 0;
            int lastLineIndex = getRecordCount() - 1;
            int selectedRecord = getSelectedRecord();
            if (selectedRecord <= lastLineIndex && selectedRecord > 0)
            {
                setSelectedRecord(selectedRecord - 1);
            }
            else
            {
                setSelectedRecord(firstLineIndex);
            }
        }
    }

    @Override
    public void next ()
    {
        if (validate())
        {
            int lastLineIndex = getRecordCount() - 1;
            int selectedRecord = getSelectedRecord();
            if (selectedRecord < 0)
            {
                int firstRecord = 0;
                setSelectedRecord(selectedRecord);
            }
            else if (selectedRecord >= 0 && selectedRecord < lastLineIndex)
            {
                setSelectedRecord(selectedRecord);
            }
            else
            {
                // last row is selected, so there is no next
            }
        }
    }

    @Override
    public void nextPage ()
    {
        alterPage(1);
    }

    @Override
    public void last ()
    {
        if (validate())
        {
            int lastLineIndex = getRecordCount() - 1;
            setSelectedRecord(lastLineIndex);
        }
    }

    private void alterPage (int factor)
    {
        int rowCount = getRecordCount();
        if (validate())
        {
            int lineHeight = getTotalHeight() / rowCount;
            int linesPerPage = getVisibleHeight() / lineHeight;
            int destinationPageRecord = getSelectedRecord() + (linesPerPage * factor);
            destinationPageRecord = validateDestinationPageRecord(rowCount, destinationPageRecord);
            setSelectedRecord(destinationPageRecord);
        }
    }

    private int validateDestinationPageRecord (int rowCount, int destinationPageRecord)
    {
        if (destinationPageRecord <= 0)
        {
            destinationPageRecord = 0;
        }
        else if (destinationPageRecord > rowCount - 1)
        {
            destinationPageRecord = rowCount - 1;
        }
        else
        {
        }
        return destinationPageRecord;
    }

    private boolean validate ()
    {
        return getRecordCount() > 0;
    }

    public abstract int getSelectedRecord ();

    public abstract void setSelectedRecord (int selectedRecord);

    public abstract int getRecordCount ();

    public abstract int getTotalHeight ();

    public abstract int getVisibleHeight ();

}
