package kitmorgan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
    String playerName;
    List<Card> hand = new ArrayList<>();
    int playerId;
    int chips;
    int seatNumber;
    boolean isPlaying = false;
    boolean isUp = false;
    boolean wonLastTrick = false;
    boolean isDealer = false;

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


//    public boolean setBid(int bid) {
//        if (bid >= 0 && bid <= hand.size()){
//            this.bid = bid;// the bid greater than or equal to zero; bid is not greater than cards dealt (round)
//            return true;
//        }
//        return false;
//    }
//    public boolean setBidAsDealer(String bid, int anythingBut, boolean overbid, int round) {
//        String checker ="";
//        for (int i = 0; i <= round; i++){
//            checker += i;
//        }
//        if (!checker.contains(bid) || bid.equalsIgnoreCase("")){
//            return false;
//        }
//        int bidNum = Integer.parseInt(bid);
//
//        if (overbid == true){
//            this.setBid(bidNum);
//            return true;
//        }else if (bidNum <= round && bidNum != anythingBut && bidNum >= 0){
//            this.setBid(bidNum);
//            return true;
//        }else {
//            return false;
//        }
//    }
//public void resetBid(){
//    this.bid = -1;
//}
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


}
