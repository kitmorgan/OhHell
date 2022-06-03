package kitmorgan;

import java.util.HashMap;
import java.util.Map;

public class Round {
Map<Player, Integer> bidMap = new HashMap<>();
Map<Player, Integer> trickTakenMap = new HashMap<>();

// should a round have a map to keep track of what players bid, or is there a better way to keep track
    // same with tricks taken '


    /** takes a map <Player player, Integer bid> of bids, returns true if the bid is valid, false if not */
    public boolean bidding (Map<Player, Integer> bidMap){
        //TODO: iterate through the list of bids and add them to the players if the bids are valid
        return true; //obv change this
    }
}
