package kitmorgan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

    public class Deck {
        private Stack<Card> cards = new Stack<>();
        private Card trump;

        // constructor
        public Deck() {

            for (Card.Suits suit : Card.Suits.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    Card card = new Card(suit, rank);
                    cards.add(card);
                }
            }

        }

        public void shuffle() {
            Collections.shuffle(cards);
        }

        public String toString() {
            String result = "";
            for (Card card : cards) {
                result += card.toString() + "\n";
            }
            return result;
        }

        /** deal cards to a list of players **/
        public void deal(List<Player> playersList, int numberOfCards) {
            ;
            shuffle();
            for (int cardIndex = 0; cardIndex < numberOfCards; cardIndex++) {
                for (Player player : playersList) {
                    player.addCard(this.cards.pop());
                }
            }
        }

        public Card getTrump() {
            return trump;
        }

        public Card pickTrump() {
            this.trump = trump;
            return this.cards.pop();
        }
    }

