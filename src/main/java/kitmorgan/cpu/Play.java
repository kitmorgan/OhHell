package kitmorgan.cpu;
import  kitmorgan.*;


public class Play {
    // algorithm for trying to capture as many tricks as possible
    public void Greedily(Round round){
        Trick trick = round.createTrick();
        Player currPlayer = trick.upNow();
        List<List<Cards>> potentialPlays = new ArrayList<>();
        //generate every combo of order, or just legal ones?
        while(true){
            // for i in cards
                // try and play the first card
            // if legal, play second etc
            // if list created length is same as hand add it to potentialPlays
        }
        // It needs to play the round over and over till it grabs the maximum number of cards
        // for (idk combos)
        // play order xyz
        // return ordered list of card play order
        // where it will need to pick the highest ranked legal one

    }
}
