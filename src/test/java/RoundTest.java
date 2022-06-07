import kitmorgan.OhHell;
import kitmorgan.Player;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class RoundTest {
    OhHell ohHell;

    @Before
    public void Setup() {
        ohHell = new OhHell(3);
    }

    @Test
    public void round_has_players() {
        // arrange
        int expected = 3;
        int output = ohHell.players.size();
        Assert.assertEquals(expected, output);
    }



}
