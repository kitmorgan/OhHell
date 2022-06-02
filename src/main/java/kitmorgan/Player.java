package kitmorgan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
    String playerName;
    List<Card> hand = new ArrayList<>();
    int playerId;
    int score = 0;
    int chips;
    int seatNumber;
    int bid = -1;
    boolean isPlaying = false;
    boolean isUp = false;
    boolean wonLastTrick = false;
    boolean isDealer = false;

    public Player(String playerName, int playerId, List<Card> hand, int score, int chips, int seatNumber) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.hand = hand;
        this.score = score;
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

    public Player(String playerName, int playerId){
        this.playerName = playerName;
        this.playerId = playerId;
    }

    // getters
    public String getPlayerName() {
        return playerName;
    }

    public int getBid() {
        return bid;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getChips() {
        return chips;
    }

    public int getScore() {
        return score;
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

    private void setScore(int score) {
        this.score = score;
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


    public boolean setBid(int bid) {
        if (bid >= 0 && bid <= hand.size()){
            this.bid = bid;// the bid greater than or equal to zero; bid is not greater than cards dealt (round)
            return true;
        }
        return false;
    }
    public boolean setBidAsDealer(String bid, int anythingBut, boolean overbid, int round) {
        String checker ="";
        for (int i = 0; i <= round; i++){
            checker += i;
        }
        if (!checker.contains(bid) || bid.equalsIgnoreCase("")){
            return false;
        }
        int bidNum = Integer.parseInt(bid);

        if (overbid == true){
            this.setBid(bidNum);
            return true;
        }else if (bidNum <= round && bidNum != anythingBut && bidNum >= 0){
            this.setBid(bidNum);
            return true;
        }else {
            return false;
        }
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

    public void resetBid(){
        this.bid = -1;
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


    public Card playCard(){
        Scanner scanner = new Scanner(System.in);
        String cardPickNumber = "";
        Card chosenCard;
        System.out.printf("%s's hand: ", this.getPlayerName());
        System.out.print(this.getHand());
        System.out.println("");
        do {
            System.out.printf("Play a card (1 - %s): ", this.getHand().size());
            cardPickNumber = scanner.nextLine();

        } while (!cardPickNumber.matches("[1-9]"));
        chosenCard = hand.get(Integer.parseInt(cardPickNumber) - 1);
        hand.remove(Integer.parseInt(cardPickNumber) - 1);
        return chosenCard;
    }
    /** asks the user for their bid, uses different logic if they are the dealer */
    public void askBid(List<String> validBids, int currentBids, int round){
        Scanner scanner = new Scanner(System.in);
        int anythingBut = round - currentBids;
        String input;
        int bid;
        boolean test = false;
        System.out.println(this.getPlayerName() + "'s hand: " + this.getHand());
        if (this.isDealer()){
            if (currentBids > round) {
                System.out.print("Alright dealer, lucky you looks like we're overbid, enter your bid: ");
            } else {
                System.out.printf("Alright dealer, it's your bid, anything but %s: ", anythingBut);
            }do{
                input = scanner.nextLine();
                if(!validBids.contains(input) || String.valueOf(anythingBut).equalsIgnoreCase(input)) {
                    System.out.print("You can't bid that. Bid again: ");
                }else{
                    this.bid = Integer.parseInt(input);
                    test = true;
                }
            }while(!test);
        }else{
            do {
                System.out.print(this.getPlayerName() + ", enter your bid: ");
                input = scanner.nextLine();
                if (validBids.contains(input)){
                    this.bid = Integer.parseInt(input);
                    test = true;
                }
            }while(!test);
        }
    }
}
