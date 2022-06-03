package kitmorgan;

import java.util.ArrayList;
import java.util.List;

public class Trick {


    // map or list for cards? or 2d array
    private List<Card> cardsPlayed = new ArrayList<>();
    private Player winner;
    // I think I need the player list
    private List<Player> players = new ArrayList<>();
    private Card trump;

    public Trick(List<Player> players, Card trump){
//To do;
    }

    //
    public Player getWinner(){
        // logic goes here
        return winner; // TODO: change me
    }
}