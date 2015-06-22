package br.com.luvva.jwheel.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CardLayoutPanel extends JPanel
{
    private final CardLayout cardLayout = new CardLayout();

    private       String                 selectedCard  = null;
    private final List<String>           cards         = new ArrayList<>();
    private final Map<String, Container> containersMap = new HashMap<>();

    private final Set<CardsPnlListener> listeners                = new HashSet<>();
    private       boolean               listenersSetIsBeingRead  = false;
    private final List<Runnable>        alterListenersSetActions = new ArrayList<>();

    private static final String SELECTED = "selected";
    private static final String REMOVED  = "removed";
    private static final String ADDED    = "added";

    public CardLayoutPanel ()
    {
        setLayout(cardLayout);
    }

    public Container getSelectedContainer ()
    {
        return containersMap.get(getSelectedCard());
    }

    public String getSelectedCard ()
    {
        return selectedCard;
    }

    public void addCardListener (final CardsPnlListener listener)
    {
        if (listenersSetIsBeingRead)
        {
            alterListenersSetActions.add(() -> listeners.add(listener));
        }
        else
        {
            listeners.add(listener);
        }
    }

    public void removeCardListener (final CardsPnlListener listener)
    {
        if (listenersSetIsBeingRead)
        {
            alterListenersSetActions.add(() -> listeners.remove(listener));
        }
        else
        {
            listeners.remove(listener);
        }
    }

    public void addCard (Container container, String cardId)
    {
        if (cardId == null)
        {
            throw new IllegalArgumentException("Card ids can't be null!");
        }
        if (cards.contains(cardId))
        {
            throw new IllegalArgumentException("Card id already registered!");
        }
        if (container == null)
        {
            throw new IllegalArgumentException("Container can't be null!");
        }
        if (cards.add(cardId))
        {
            add(container, cardId);
            containersMap.put(cardId, container);
            notifyListeners(ADDED, cardId);
        }
    }

    public boolean setSelectedCard (String cardId)
    {
        if (Objects.equals(this.selectedCard, cardId))
        {
            return false;
        }
        if (!cards.contains(cardId))
        {
            throw new IllegalArgumentException("CardId not found!");
        }
        else
        {
            cardLayout.show(this, cardId);
            this.selectedCard = cardId;
            revalidate();
            repaint();
            notifyListeners(SELECTED, cardId);
            return true;
        }
    }

    public void removeCard (String cardId)
    {
        removeCard(cardId, false);
    }

    public void removeAllCards ()
    {
        for (String cardId : containersMap.keySet())
        {
            removeCard(cardId, true);
        }
        containersMap.clear();
        notifyListeners(SELECTED, null);
    }

    private void removeCard (String cardId, boolean removingAll)
    {
        if (cards.contains(cardId))
        {
            String nextCardId = getNextCardId(cardId);
            cardLayout.removeLayoutComponent(containersMap.get(cardId));
            remove(containersMap.get(cardId));
            cards.remove(cardId);
            notifyListeners(REMOVED, cardId);
            if (!removingAll)
            {
                containersMap.remove(cardId);
                notifyListeners(SELECTED, nextCardId);
            }
        }
        else
        {
        }
    }

    private String getNextCardId (String cardId)
    {
        if (cards.size() == 1)
        {
            return null;
        }
        else
        {
            String nextCardId = null;
            for (int i = 0; i < cards.size(); i++)
            {
                String card = cards.get(i);
                if (cardId.equals(card))
                {
                    if (i == cards.size() - 1)
                    {
                        nextCardId = cards.get(0);
                    }
                    else
                    {
                        nextCardId = cards.get(i + 1);
                    }
                    break;
                }
            }
            return nextCardId;
        }
    }

    private void notifyListeners (String eventType, String cardId)
    {
        try
        {
            listenersSetIsBeingRead = true;
            for (CardsPnlListener cardsPnlListener : listeners)
            {
                switch (eventType)
                {
                    case ADDED:
                        cardsPnlListener.cardAdded(cardId);
                        break;
                    case REMOVED:
                        cardsPnlListener.cardRemoved(cardId);
                        break;
                    case SELECTED:
                        cardsPnlListener.cardSelected(cardId);
                        break;
                    default:
                }
            }
            alterListenersSetActions.forEach(java.lang.Runnable::run);
            alterListenersSetActions.clear();
        }
        finally
        {
            listenersSetIsBeingRead = false;
        }
    }

    @Override
    public boolean requestFocusInWindow ()
    {
        Container selCont = getSelectedContainer();
        if (selCont == null)
        {
            return super.requestFocusInWindow();
        }
        else
        {
            return selCont.requestFocusInWindow();
        }
    }

    public interface CardsPnlListener
    {
        void cardAdded (String cardId);

        void cardRemoved (String cardId);

        void cardSelected (String cardId);
    }

}
