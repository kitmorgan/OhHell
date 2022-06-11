package kitmorgan;

import java.util.*;

public class  Player {
    public String playerName;
    public List<Card> hand = new ArrayList<>();
    private int playerId;
    private int chips;
    public int seatNumber;
    private boolean isPlaying = false;
    private boolean isUp = false;
    private boolean wonLastTrick = false;
    public boolean isDealer = false;
    public boolean hasActed = false;


    public void hasActed(boolean actedTrue) {
        this.hasActed = hasActed;
    }

    public Player(String playerName, int playerId, List<Card> hand, int chips, int seatNumber) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.hand = hand;
        this.chips = chips;
        this.seatNumber = seatNumber;
        isPlaying = true;
    }

    public Player(String playerName, int playerId, int seatNumber){
        this.playerName = playerName;
        this.playerId = playerId;
        this.seatNumber = seatNumber;
        isPlaying = true;
    }

    public Player(String playerName, int seatNumber){
        this.playerName = playerName;
        this.seatNumber = seatNumber;
    }

    // getters
    public String getPlayerName() {
        return playerName;
    }


    public int getPlayerId() {
        return playerId;
    }

    public int getChips() {
        return chips;
    }


    public int getSeatNumber() {
        return seatNumber;
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isUp() {
        return isUp;
    }

    public boolean wonLastRound(){ return wonLastTrick;}

    public boolean isDealer() {
        return isDealer;
    }

    // setters
    public void sitDown() {
        isPlaying = true;
    }

    public void setWonLastTrick(boolean won) {
        this.wonLastTrick = won;
    }
    public void standUp() {
        isPlaying = false;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    private boolean addChips(int addChips) {
        if (addChips > 0) {
            chips += addChips;
            return true;
        } else {
            return false;
        }
    }



    /**
     * sets chips to (chips - subtractChips) (enter a positive integer) and returns true if chips subtracted
     **/
    private boolean subtractChips(int subtractChips) {
        if (chips - subtractChips >= 0) {
            chips -= subtractChips;
            return true;
        } else {
            return false;
        }
    }

    public void chooseSeat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public String handToString(){
        String output = "";
        for(int i = 0; i < hand.size(); i++){
            output += (i+1) + ".)" + hand.get(i).toString() + " ";
        }
        return output;
    }
    // @overide.equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return seatNumber == player.seatNumber && playerName.equals(player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, seatNumber);
    }

}
