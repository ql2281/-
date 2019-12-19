package OOD.PokerGame;

public class BlackJackHand extends Hand {
    public BlackJackHand(String id) {
        super(id);
    }

    private int score() {
        return 21;
    }

    public boolean isBursted() {
        return score() > 21;
    }

    public boolean is21() {
        return score() == 21;
    }

    public boolean isBJ() {
        return is21() && handCards.size() == 0;
    }
}
