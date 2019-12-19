package OOD.PokerGame;
import java.sql.SQLOutput;
import java.util.*;

public class Game {
    public static void main(String[] args) {
        Deck deck = new Deck();
        List<BlackJackHand> listHands = new ArrayList<>();
        boolean end = false;
        int numPlayer = 2;

        deck.shuffle();
        // System.out.println(deck.toString());

        // 创建玩家，加入到玩家列表
        for (int i = 0; i < numPlayer; i++) {
            listHands.add(new BlackJackHand("Player " + i));
        }

        // TODO: card game logic
        for (BlackJackHand hand: listHands) {
            List<Card> cards = deck.deal(5);
            hand.addCard(cards);
            // System.out.println(hand.toString());
        }

        while (!end) {
            // 如果牌全部发完，结束游戏
            if (deck.getCardList().size() == 0) {
                end = true;
                System.out.println("No one wins!");
            }

            // 每回合发一张牌
            for (int i = 0; i < numPlayer; i++) {
                listHands.get(i).addCard(deck.deal());
            }

            // 每个玩家随机出一张牌
            for (BlackJackHand hand: listHands) {
                Random rand = new Random();
                int idx = rand.nextInt(hand.getHandCards().size());
                hand.removeCard(idx);
            }

            // 判定是不是BlackJack
            for (BlackJackHand hand: listHands) {
                if (hand.is21()) {
                    end = true;
                    System.out.println("The winner is " + hand.getUserId());
                    break;
                }
            }
        }
    }
}
