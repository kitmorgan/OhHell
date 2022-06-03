import kitmorgan.OhHell;
import kitmorgan.Round;
import org.junit.Assert;
import org.junit.Test;

public class OhHellTest {

    @Test
    public void creates_a_list_of_3_players() {
        // arrange
        int numberOfPlayers = 3;
        OhHell ohhell = new OhHell(numberOfPlayers);
        boolean result = numberOfPlayers == ohhell.players.size();
        // act
        Assert.assertTrue("should be a list of size 3", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_error_when_less_than_3_players(){
        // arrange
        int numberOfPlayers = 2;
        OhHell ohHell = new OhHell(numberOfPlayers);
    }
    @Test(expected = IllegalArgumentException.class)
    public void should_throw_error_when_greater_than_7_players(){
        // arrange
        int numberOfPlayers = 10;
        OhHell ohHell = new OhHell(numberOfPlayers);
    }
    @Test(expected = IllegalArgumentException.class)
    public void should_throw_error_when_negative_players(){
        // arrange
        int numberOfPlayers = -5;
        OhHell ohHell = new OhHell(numberOfPlayers);
    }

    @Test
    public void create_round_should_deal_3_cards_and_have_trump(){
        OhHell ohHell = new OhHell(3);
        Round round = ohHell.createRound(3, true);
        Round testRound = new Round(ohHell.players, 3, true);

        Assert.assertTrue("Rounds should be the same", testRound.getPlayers() == round.getPlayers());
    }
}
