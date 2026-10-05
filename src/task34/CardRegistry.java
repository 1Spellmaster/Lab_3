package task34;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CardRegistry {
    private final Map<Integer, TravelCard> cards = new HashMap<>();
    private int nextId = 1;

    public TravelCard issueCard(CardType cardType, TicketType ticketType, LocalDate startDate) {
        TravelCard card = new TravelCard(nextId, cardType, ticketType, startDate);
        cards.put(nextId, card);
        nextId++;
        return card;
    }

    public boolean contains(TravelCard card) {
        return card != null && cards.get(card.getId()) == card;
    }
}
