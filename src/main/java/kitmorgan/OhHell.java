package kitmorgan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OhHell {
    public List<Round> rounds = new ArrayList<>();
    public List<Player> players = new ArrayList<>();
    private final int numberOfPlayers;
    private final int numberOfRounds;
    Map<Player, Integer> scoreboard = new HashMap<>();
    public int roundNumber = 1;
    public boolean trumpThisRound = true;
    public int dealerIndex = 0;


    // numberOfPlayers should be >= 3 && <=7 throws IllegalArgumentException
    public OhHell(int numberOfPlayers, int numberOfRounds) {
        if (numberOfPlayers < 3 || numberOfPlayers > 7) {
            throw new IllegalArgumentException("Invalid number of players: " + numberOfPlayers);
        }
        createPlayers(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfRounds = numberOfRounds;
    }

    public OhHell(int numberOfPlayers) {
        this(numberOfPlayers, 52 / numberOfPlayers);
    }

    // getters

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    // generate list of players
    // logic for betting

    /**
     * generates the players and the scoreboard (seat number is the key, score is the value
     */
    public void createPlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player("Player" + (i + 1), i);
            players.add(player);
        }
        players.get(numberOfPlayers - 1).isDealer = true;
    }



    // controls when/if there is a no trump round
    public boolean isTrumpThisRound() {
        if (roundNumber < numberOfRounds) {
            this.trumpThisRound = true;
            return true;
        }
        if (roundNumber >= numberOfRounds) {
            this.trumpThisRound = false;
            return false;
        }
        return true;
    }

    // iterate through players if hasActed = true
    // needs rework
    public boolean hasNextToAct() {
        for (Player player : players) {
            if (!player.hasActed) {
                return true;
            }
        }
        return false;
    }

    public int getFirstActorIndex() {
        int dealerPlusOne = -99;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isDealer()) {
                dealerPlusOne = i + 1;
            }
        }
        int firstUp = dealerPlusOne % players.size();
        return firstUp;
    }
    public String getDealerName() throws Exception {
        String answer = "No dealer";
        for (Player player : players) {
            if (player.isDealer()) {
                answer = player.getPlayerName();
            }
        }
        if (answer.equals("No dealer")) {
            throw new Exception("No one to deal!");
        }
        return answer;
    }

    public Player getUpNow(){
        int startPlayer = getFirstActorIndex();
        Player isUp = players.get(startPlayer);
        for(int i = 0; i < players.size(); i++){
            int playerIndex = startPlayer + i % getNumberOfPlayers();
            if (!players.get(playerIndex).hasActed){
                return players.get(playerIndex);
            }
        }
        return isUp;
    }

    public boolean hasNextRound() {
        return rounds.size() < numberOfRounds;
    }

    public void scoreRound(Map<Player, RoundInfo> roundInfoMap){
        for(Map.Entry<Player, RoundInfo> entry : roundInfoMap.entrySet()){
            Player player = entry.getKey();
            RoundInfo roundInfo = entry.getValue();
            if(!scoreboard.containsKey(player)){
                scoreboard.put(player, 0);
            }
            if(roundInfo.madeBid()){
                int score = scoreboard.get(player);
                scoreboard.put(player, score + 10);
            }
        }
        nextRound();
    }

    public void nextRound() {
        this.roundNumber++;
        dealerIndex++;
    }

    public Round createRound() {
        int modDealerIndex = (dealerIndex) % (numberOfPlayers);
        return new Round(players, this.roundNumber, modDealerIndex, trumpThisRound);
    }

    public Player gloriousVictor(){
        int highScore = 0;
        Player winner = null;
        for(Map.Entry<Player, Integer> entry : scoreboard.entrySet()){
            Player player = entry.getKey();
            Integer score = entry.getValue();
            if (score > highScore){
                highScore = score;
                winner = player;
            }
        }
        return winner;
    }
    /** Game order:
     * initialize OhHell
     * optionally get player names
     * create Round in a for loop where each index is the round number and optional logic for hasTrump;
     * inside is one bidding round where each player bids starting left of dealer, ending on dealer (there is logic in the getBid for if the player is the dealer);
     * the round then continues with the trick playCard for each player roundNumber number of times. (Ex: round 10 there are 10 tricks that need to be created.)
     * when the round ends, score is tallied based on if
     */

}

