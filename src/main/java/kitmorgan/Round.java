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

    public int trickNumber = tricks.size();

    public int bidsTaken = 0;

    Player dealer;

    public int modDealerIndex;

    int currentPlayerIndex = 0; //(getUpFirstThisTrickIndex() + 1) % players.size();


    public Card getTrump() {
        return trump;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * initializes a round, taking a list of players, the roundNumber and a boolean to draw or not draw trump
     */
    public Round(List<Player> players, int roundNumber, int modDealerIndex, boolean hasTrump) {
        this.players = players;
        this.hasTrump = hasTrump;
        this.trump = deal(roundNumber, hasTrump);
        this.roundNumber = roundNumber;
        this.dealer = players.get(modDealerIndex);
        this.modDealerIndex = modDealerIndex;
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

    /**
     * sets bid for one player, returns false if the bid was not valid;
     */
    public boolean setBid(int bid, Player player) {
        if (isDealer(player)) {
            int anythingBut = getInvalidDealerBid();
            if (bid != anythingBut && bid >= 0 && bid <= roundNumber) {
                RoundInfo roundInfo = roundInfoMap.get(player);
                roundInfo.setBid(bid);
                roundInfoMap.put(player, roundInfo);
                bidsTaken++;
            } else {
                return false;
            }
        } else if (bid >= 0 && bid <= roundNumber) {
            RoundInfo roundInfo = roundInfoMap.get(player);
            roundInfo.setBid(bid);
            roundInfoMap.put(player, roundInfo);
            bidsTaken++;
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
            if (!player.isDealer()) { //TODO: Change like above;
                bidSoFar += roundInfoMap.get(player).getBid();
            }
        }
        return roundNumber - bidSoFar;
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
// TODO: TEST ME!
    public int getUpFirstThisTrickIndex() {
        Player winnerLast = null;
        int answer = -1;
        if (trickNumber == 0) {
            int index = (modDealerIndex + 1) % players.size();
            return index;
        }
        for (int i = 0; i < tricks.size(); i++) {
            winnerLast = tricks.get(i).getWinner();
        }
        for (int j = 0; j < players.size(); j++) {
            if (winnerLast.equals(players.get(j))) {
                answer = j;
            }
        }
        return answer;
    }



    public boolean isDealer(Player player ){
        return (player == dealer);
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


    public Trick createTrick() {
        Trick trick = new Trick(players, getTrump(), trickNumber, hasTrumpBeenPlayed());
        return trick;
    }

    public void endTrick(Trick trick) throws Exception{
        if(trick.cardsPlayed.size() == players.size()){
            trick.getWinner();
            addTrick(trick);
        }else {
            throw new Exception("THE TRICK IS NOT OVER UNTIL ALL PLAYERS HAVE PLAYED: " + trick.cardsPlayed.size() + "!=" + players.size());
        }

    }

}
