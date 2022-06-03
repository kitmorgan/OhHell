package kitmorgan;

import java.util.ArrayList;
import java.util.List;

public class Hand{
    private  List<Card> hand = new ArrayList<>();

    public void addCard(Card card){
        hand.add(card);
    }
    public void removeCard(Card card){
        hand.remove(card);
    }
    public void removeCard(int slot){
        hand.remove(slot);
    }
    public String toString(){
        String  output = "";
        for (Card card : hand){
            output += card.toString() + " ";
        }
        return output;
    }


}
