package kitmorgan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round {
    public Map<Player, RoundInfo> roundInfoMap = new HashMap<>();
    private List<Player> players = new ArrayList<>();
    private final Card trump;
    private final boolean hasTrump;
    private final int roundNumber;
    public List<Trick> tricks = new ArrayList<>();
    public boolean trumpPlayed;
    private boolean overBid = false;
    private boolean hasNextTrick = true;


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
        for (Player player : players) {
            RoundInfo roundInfo = new RoundInfo(0, 0);
            roundInfoMap.put(player, roundInfo);
        }
    }

    /**
     * deals roundNumber of cards to each player, if drawTrump is true it will return a trump card else it will return null
     */
    public Card deal(int roundNumber, boolean drawTrump) {
        Deck deck = new Deck();
        deck.shuffle();
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
        if (player.isDealer()) {
            int anythingBut = getInvalidDealerBid();
            if (bid != anythingBut && bid >= 0 && bid <= roundNumber) {
                RoundInfo roundInfo = roundInfoMap.get(player);
                roundInfo.setBid(bid);
                roundInfoMap.put(player, roundInfo);
                player.hasActed = true;
            } else {
                return false;
            }
        } else if (bid >= 0 && bid <= roundNumber) {
            RoundInfo roundInfo = roundInfoMap.get(player);
            roundInfo.setBid(bid);
            roundInfoMap.put(player, roundInfo);
            player.hasActed = true;
        } else {
            return false;
        }
        return true;
    }

    /**
     * gets invalid bid for dealer
     */
    public int getInvalidDealerBid() {
        int bidSoFar = 0;
        for (Player player : players) {
            if (!player.isDealer()) {
                bidSoFar += roundInfoMap.get(player).getBid();
            }
        }
        return roundNumber - bidSoFar;
    }

    public void setDealer(Player dealer) {
        for (Player player : players) {
            if (player == dealer) {
                player.isDealer = true;
            } else {
                player.isDealer = false;
            }
        }
    }

    public int getBidSoFar() {
        int bidSoFar = 0;
        for (Player player : players) {
            if (!player.isDealer()) {
                bidSoFar += roundInfoMap.get(player).getBid();
            }
        }
        return bidSoFar;
    }

    public boolean isOverBid() {
        if(getBidSoFar() > roundNumber){
            this.overBid = true;
        }
        return getBidSoFar() > roundNumber;
    }

    public void addTrick(Trick trick) {
        tricks.add(trick);
    }

    public boolean hasTrumpBeenPlayed() {
        boolean output = false;
        if (tricks.size() == 0) {
            return false;
        }
        for (Trick trick : tricks) {
            if (trick.hasTrumpBeenPlayed()) {
                output = true;
            }
        }
        return output;
    }

    public int getUpThisTrickIndex() {
        Player winnerLast = null;
        int answer = -1;
        if (tricks.size() == 0) {
            int index = findDealerIndex() + 1;
            return index;
        }
        for (int i = 0; i < tricks.size(); i++) {
            winnerLast = tricks.get(i).getWinner();
        }
        for (int j = 0; j < players.size(); j++) {
            if (winnerLast.equals(players.get(j))) {
                answer = j + 1;
            }
        }
        return answer;
    }

    public Player findDealer() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isDealer()) {
                return players.get(i);
            }
        }
        return null;
    }
    public boolean hasNextTrick(){
        if(tricks.size() < roundNumber - 1){
            hasNextTrick = true;
            return true;
        }else{
            hasNextTrick = false;
            return false;
        }
    }

    public int findDealerIndex() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isDealer()) {
                return i;
            }
        }
        return -1;
    }

    public void resetHasActed(){
        for (Player player : players){
            player.hasActed = false;
        }
    }

    public Trick createTrick() {
        Trick trick = new Trick(players, getTrump(), hasTrumpBeenPlayed());
        resetHasActed();
        return trick;
    }
}
