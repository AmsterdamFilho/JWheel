package br.com.luvva.jwheel.view.swing.extension;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class CardLayoutPanel extends JPanel
{
    private final CardLayout cardLayout = new CardLayout();

    private       String                 selectedCard  = null;
    private final Map<String, Container> containersMap = new LinkedHashMap<>();
    private final Set<CardsPnlListener>  listeners     = new HashSet<>();

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
        listeners.add(listener);
    }

    public void removeCardListener (final CardsPnlListener listener)
    {
        listeners.remove(listener);
    }

    public void addCard (Container container, String cardId)
    {
        if (cardId == null)
        {
            throw new IllegalArgumentException("Card ids can't be null!");
        }
        if (containersMap.containsKey(cardId))
        {
            throw new IllegalArgumentException("Card id already registered!");
        }
        if (container == null)
        {
            throw new IllegalArgumentException("Container can't be null!");
        }
        add(container, cardId);
        containersMap.put(cardId, container);
        notifyListeners(ADDED, cardId);
    }

    public void setSelectedCard (String cardId)
    {
        //noinspection StatementWithEmptyBody
        if (Objects.equals(this.selectedCard, cardId))
        {
        }
        else if (!containsCard(cardId))
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
        }
    }

    private void removeContainer (Container container)
    {
        cardLayout.removeLayoutComponent(container);
        remove(container);
    }

    public void removeCard (String cardId)
    {
        removeContainer(containersMap.get(cardId));
        containersMap.remove(cardId);
        notifyListeners(REMOVED, cardId);
    }

    public void removeAllCards ()
    {
        containersMap.forEach((cardId, container) -> removeContainer(container));
        List<String> cardsIds = new ArrayList<>(containersMap.keySet());
        containersMap.clear();
        cardsIds.forEach(cardId -> notifyListeners(REMOVED, cardId));
        this.selectedCard = null;
        notifyListeners(SELECTED, null);
    }

    private void notifyListeners (String eventType, String cardId)
    {
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
    }

    public boolean containsCard (String cardID)
    {
        return containersMap.containsKey(cardID);
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
