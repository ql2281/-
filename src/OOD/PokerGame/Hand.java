package OOD.PokerGame;
import java.util.*;

public class Hand {
    private final String userId;
    protected List<Card> handCards;
    public Hand(String id) {
        this.userId = id;
        handCards = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card: handCards) {
            sb.append(card.toString()).append("\n");
        }
        return new String(sb);
    }

    public void addCard(Card card) {
        // cc
        handCards.add(card);
    }

    // overload
    public void addCard(List<Card> cards) {
        for (Card card: cards) {
            addCard(card);
        }
    }

    public Card removeCard(int idx) {
        if (idx < 0 || idx >= handCards.size()) return null;
        return handCards.remove(idx);
    }
}
