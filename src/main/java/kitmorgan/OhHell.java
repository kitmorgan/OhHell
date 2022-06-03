package kitmorgan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OhHell {
    Round round;
    public List<Player> players = new ArrayList<>();
    private final int numberOfPlayers;
    private final int numberOfRounds;
    Card trump;
    Map<Integer, Integer> scoreboard = new HashMap<>();

    // numberOfPlayers should be >= 3 && <=7 throws IllegalArgumentException
    public OhHell(int numberOfPlayers, int numberOfRounds){
        if (numberOfPlayers < 3 || numberOfPlayers > 7) {
            throw new IllegalArgumentException("Invalid number of players: " + numberOfPlayers);
        }
        createPlayers(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfRounds = numberOfRounds;
    }
    public OhHell(int numberOfPlayers){
        this(numberOfPlayers, 52/numberOfPlayers);
    }

    // getters

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }



    // generate list of players
    // logic for betting

    /** generates the players and the scoreboard (seat number is the key, score is the value */
    public void createPlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player("Player" + (i + 1), i);
            players.add(player);
            scoreboard.put(i, 0);
        }
    }
/** deals roundNumber of cards to each player, if drawTrump is true it will return a trump card else it will return null */
    public Card deal(int roundNumber, boolean drawTrump){
        Deck deck = new Deck();
        for (int cardIndex = 0; cardIndex < roundNumber; cardIndex++){
            for(Player player : players){
                player.addCard(deck.cards.pop());
            }
        }
        if(drawTrump){
            return deck.cards.pop();
        }else {
            return null;
        }
    }
}
