package kitmorgan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trick {


    private Player winner;
    private List<Player> players = new ArrayList<>();
    private Card trump;
    Card.Suits trumpSuit;
    private Map<Card, Player> cardsPlayed = new HashMap<>();
    private boolean hasTrumpBeenPlayed = false;
    private Card firstCard;

    public Trick(List<Player> players, Card trump, Boolean hasTrumpBeenPlayed){
        this.players = players;
        this.trump = trump;
        this.hasTrumpBeenPlayed = hasTrumpBeenPlayed;
        trumpSuit = trump.getSuit();
    }

    public boolean playCard(Player player, Card card){
        boolean canPlayTrump = true;
        boolean canPlayOtherSuit = true;

        Card.Suits mandatorySuit = trump.getSuit();
        // first player can play any card except trump, unless trump has been played.
        for(Card cardInHand: player.hand){
            // does the hand contain non-trump cards
            if (cardInHand.getSuit() != trumpSuit){
                canPlayTrump = false;
            }
            // does the hand contain the suit of first card played
            if (!cardsPlayed.isEmpty()){
                if(cardInHand.getSuit() == mandatorySuit){
                    canPlayOtherSuit = false;
                    break;
                }
            }
        }
        if (cardsPlayed.isEmpty()){
            // is the card a trump card?
            if (hasTrumpBeenPlayed || canPlayTrump){
                cardsPlayed.put(card, player);
                setHasTrumpBeenPlayed();
                firstCard = card;
                mandatorySuit = card.getSuit();

            } else return  false;
        }else{
            // does the player have the mandatory suit?
            if(canPlayOtherSuit){
                cardsPlayed.put(card, player);
                setHasTrumpBeenPlayed();
            }else if(card.getSuit() != mandatorySuit){
                return false;
            }
        }
        return false;
    }

    public Player getWinner(){
        return bestCard();
    }

    public Player bestCard() {
        Card bestCard = firstCard;
        // picking strongest suit
        if(trump != null) {
            for (Map.Entry<Card, Player> entry : cardsPlayed.entrySet()) {
                if (entry.getKey().getSuit() == trump.getSuit()) {
                    bestCard.suit = trump.getSuit();
                    break;
                }
            }
        }
        for (Map.Entry<Card, Player> entry : cardsPlayed.entrySet()) {
            if (entry.getKey().getSuit() == bestCard.suit && entry.getKey().rank.ordinal() > bestCard.rank.ordinal()) {
                bestCard = entry.getKey();
            }
        }
        return cardsPlayed.get(bestCard);
    }

    public void setHasTrumpBeenPlayed(){
        for (Map.Entry<Card, Player> entry : cardsPlayed.entrySet()){
            if (entry.getKey().getSuit() == trumpSuit){
                hasTrumpBeenPlayed = true;
            }
        }
    }

}