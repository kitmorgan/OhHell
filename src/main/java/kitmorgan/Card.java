package kitmorgan;

public class Card {

    private Suits suit;
    private Rank rank;
    private boolean faceDown = true;

    public Card(Suits suit, Rank rank) {
        this(suit, rank, false);
    }

    public Card(Suits suit, Rank rank, boolean showFaceUp) {
        this.suit = suit;
        this.rank = rank;
        this.faceDown = !showFaceUp;
    }

    public enum Suits {
        CLUBS,
        SPADES,
        HEARTS,
        DIAMONDS
    }
    // the position of each enum is the order for comparison, do not change
    public enum Rank {
        DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private int priority;

        Rank(int priority) {
        }
    }

    public Suits getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public String cardOrientation() {
        return "the care is " + (faceDown ? "face down" : "face up");
    }

    public void flip() {
        this.faceDown = !faceDown;
    }

    public String toString() {
        return String.format("%s of %s", rank, suit);
    }
    public boolean isHigher(Card compare){
        if (this.rank.ordinal() > compare.rank.ordinal()){
            return true;
        }else{ return false;}
    }
}
