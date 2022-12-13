package kitmorgan.cpu;
import  kitmorgan.*;


public class Setup {
    List<Player> players = new ArrayList<>();
    players.add(new Player("player1", 1));
    players.add(new Player("player2", 2));
    players.add(new Player("player3", 3));
    players.add(new Player("player4", 4));

    int roundNumber = 2;
    int dealerIndex = 1;
    boolean hasTrump = true;
    Round round = new Round(players, roundNumber, modDealerIndex, hasTrump);
    Trick trick = round.createTrick();
}
