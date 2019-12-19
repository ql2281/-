package OOD.PokerGame;

public class Card {
    private final int val;
    private final Suit suit;
    public Card(int val, Suit suit) {
        this.val = val;
        this.suit = suit;
    }

    public int getVal() {
        return val;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return val + ", " + suit;
    }
}
