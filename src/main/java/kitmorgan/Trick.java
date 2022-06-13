package kitmorgan;

import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trick {


    private Player winner;
    public List<Player> players = new ArrayList<>();
    private final Card trump;
    Card.Suits trumpSuit;
    public Map<Card, Player> cardsPlayed = new HashMap<>();
    private boolean hasTrumpBeenPlayed;

    private int trickNumber = 0;
    private int cardsPlayedCounter = 0;

    private Card.Suits firstSuitPlayed;
    int currentPlayerIndex;

    public Trick(List<Player> players, Card trump, int trickNumber, Boolean hasTrumpBeenPlayed, int upFirst){
        this.players = players;
        this.trump = trump;
        this.hasTrumpBeenPlayed = hasTrumpBeenPlayed;
        trumpSuit = trump.getSuit();
        this.trickNumber = trickNumber;
        this.currentPlayerIndex = upFirst;
    }

    public Player upNow() {
        return players.get(currentPlayerIndex);
    }

    //v2 -- is the card legal?
    public boolean canPlayCard(Player player, int cardIndex){
        Card card = player.getHand().get(cardIndex);
        // special logic for first player
        if(trickNumber == 0 && cardsPlayedCounter == 0){
            boolean canPlayTrump = true;
            for(Card cardEntry : player.getHand()){
                if(cardEntry.getSuit() != trumpSuit){
                    canPlayTrump = false;
                }
            }
            if(card.getSuit() == trumpSuit && !canPlayTrump){
                return false;
            }else{
                return true;
            }
            //rules for those after the first player
        }else{
            boolean mustPlayFirstSuit = false;
            //check if they have the firstSuitPlayed
            for(Card cardEntry : player.getHand()){
                if(cardEntry.getSuit() == firstSuitPlayed){
                    mustPlayFirstSuit = true;
                }
            }
            if(mustPlayFirstSuit && card.getSuit()==firstSuitPlayed){
                return true;
            }else if(!mustPlayFirstSuit){
                return true;
            }
        }
        return false;
    }
    // check to see if the card is playable using canPlay();
    public void playCard(int indexCard){
        Player player = upNow();
        if(!canPlayCard(player, indexCard)){
            throw new IllegalArgumentException ("card not valid");
        }
        Card card = player.getHand().get(indexCard);
        cardsPlayed.put(card, player);
        player.getHand().remove(indexCard);
        if(cardsPlayedCounter == 0){
            firstSuitPlayed = card.getSuit();
        }
        cardsPlayedCounter++;
        currentPlayerIndex+= 1 % players.size();
    }

    public Player getWinner() {
        Card bestCard = new Card(firstSuitPlayed, Card.Rank.DEUCE);
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

    public boolean hasTrumpBeenPlayed(){
        setHasTrumpBeenPlayed();
        return hasTrumpBeenPlayed;
    }

    public String playedCardsToString(){
        String output = "";
        for(Map.Entry<Card, Player> entry : cardsPlayed.entrySet()){
            output += entry.getValue().toString();
        }
        return output;
    }

    public boolean trickHasNextToAct(){
        if(cardsPlayedCounter < players.size()){
            return true;
        }return false;
    }


}