package OOD.PokerGame;
import java.util.*;

public class Deck {
    private List<Card> cardList;
    public Deck() {
        cardList = new ArrayList<>();
        for (Suit suit: Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                cardList.add(new Card(i, suit));
            }
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card: cardList) {
            sb.append(card.toString()).append("\n");
        }
        return new String(sb);
    }

    // 保证每个点被取到的概率都是1/52
    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < cardList.size(); i++) {
            int j = rand.nextInt(cardList.size() - i) + i;
            Card card1 = cardList.get(i);
            Card card2 = cardList.get(j);
            cardList.set(i, card2);
            cardList.set(j, card1);
        }
    }

    public Card deal() {
        return cardList.remove(cardList.size() - 1);
    }

    // overload 如果想发多张牌
    public List<Card> deal(int num) {
        List<Card> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(deal());
        }
        return list;
    }
}
