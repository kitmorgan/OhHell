package kitmorgan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round {
    private Map<Player, Integer> bidMap = new HashMap<>();// Map<Player, roundInfo>
    private Map<Player, Integer> trickTakenMap = new HashMap<>();
    private List<Player> players = new ArrayList<>();
    private final Card trump;
    private final boolean hasTrump;
    private final int roundNumber;
    public List<Trick> tricks;

    public Map<Player, Integer> getBidMap() {
        return bidMap;
    }

    public Map<Player, Integer> getTrickTakenMap() {
        return trickTakenMap;
    }

    public Card getTrump() {
        return trump;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * initializes a round, taking a list of players, the roundNumber and a boolean to draw or not draw trump
     */
    public Round(List<Player> players, int roundNumber, boolean hasTrump) {
        this.players = players;
        this.hasTrump = hasTrump;
        this.trump = deal(roundNumber, hasTrump);
        this.roundNumber = roundNumber;
        for (Player player : players){
            bidMap.put(player, null);
            trickTakenMap.put(player, 0);
        }
    }

    /**
     * deals roundNumber of cards to each player, if drawTrump is true it will return a trump card else it will return null
     */
    public Card deal(int roundNumber, boolean drawTrump) {
        Deck deck = new Deck();
        for (int cardIndex = 0; cardIndex < roundNumber; cardIndex++) {
            for (Player player : players) {
                player.addCard(deck.cards.pop());
            }
        }
        if (drawTrump) {
            return deck.cards.pop();
        } else {
            return null;
        }
    }
// should a round have a map to keep track of what players bid, or is there a better way to keep track
    // same with tricks taken '

    /**
     * sets bid for one player, returns false if the bid was not valid;
     */
    public boolean setBid(int bid, Player player) {
        if (player.isDealer()){
            int anythingBut = getInvalidDealerBid();
            if (bid != anythingBut && bid >= 0 && bid <=roundNumber){
                bidMap.put(player, bid);
            }
            else{ return false;}
        }else if (bid >= 0 && bid <= roundNumber){
            bidMap.put(player, bid);
            return true;
        }
        return false;
    }

    /** gets invalid bid for dealer */
    public int getInvalidDealerBid(){
        int bidSoFar = 0;
        for(Player player : players){
            if(!player.isDealer()){
                bidSoFar += bidMap.get(player);
            }
        }
        return roundNumber - bidSoFar;
    }

   // logic for playing card
    // need to be able to know who played the card, add the card to playedCards
    //

   // winning a trick
   // winning points

}
