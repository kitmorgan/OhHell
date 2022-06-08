package kitmorgan;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OhHellUserInterface {
    public static void main(String[] args) throws Exception {
        OhHell ohHell = null;
        Round round = null;
        Trick trick = null;
        Scanner scanner = new Scanner(System.in);
        // get user input on how many players
        while (true) {
            try {
                write("Welcome to Oh Hell, how many players? (3 - 7): ");
                String input = scanner.nextLine();
                ohHell = new OhHell(Integer.parseInt(input));
                break;
            } catch (Exception e) {
                write("Enter a valid integer 3 - 7");
            }
        }
        // get their names
        write("Alright lets get this game started, enter your names.");
        List<String> enteredNames = new ArrayList<>();
        for (Player player : ohHell.players) {
            while (true) {
                System.out.printf("Player %s, what is your name?: ", player.getSeatNumber() + 1);
                String name = scanner.nextLine();
                if (enteredNames.contains(name)) {
                    write("Enter a unique name");
                } else if (player.playerName.length() != 0 && player.playerName.length() <= 15) {
                    player.playerName = name;
                    enteredNames.add(name);
                    break;
                } else {
                    write("Pick a valid name. Cannot be zero length or longer than 15 characters.");
                }
            }
        }
        wipe();
        write("Input accepted, starting game for:");
        write(ohHell.toStringPlayers());
        // start the game, for loop creating rounds
        for (int i = 1; i < ohHell.getNumberOfRounds(); i++) {
            Boolean hasTrump = true;
            if (i < ohHell.getNumberOfRounds() - 1) {
                hasTrump = true;
            } else {
                hasTrump = false;
            }
            // create a round
            round = ohHell.createRound(i, hasTrump);
            // deal
            System.out.printf(ohHell.getDealer() + " is dealing! Press enter to deal...");
            scanner.nextLine();
            write("");
            write("READ ME: '" + round.getTrump().toString() + " is the trump card, lets start bidding.");

            System.out.printf("%s is the first to bid', pass the computer to them.\n", ohHell.toStringFirstActor());
            write("press enter when ready (hand will be exposed)");
            scanner.nextLine();
            wipe();
            for (int j = 0; j < ohHell.getNumberOfPlayers(); j++) {
                int upNow = (ohHell.getFirstActorIndex() + j) % ohHell.getNumberOfPlayers();
                write("Trump: " + round.getTrump().toString());
                write("Current bid is at: " + round.getBidSoFar());
                write(round.getPlayers().get(upNow).handToString());
                if (round.getPlayers().get(upNow).isDealer()) {
                    if (round.getInvalidDealerBid() <= -1) {
                        write("Alright dealer we're overbid, lucky you.");
                    } else {
                        System.out.println("Dealer is up, you can't bid: " + round.getInvalidDealerBid());
                    }
                }
                while (true) {
                    System.out.printf("%s, enter your bid: ", round.getPlayers().get(upNow).getPlayerName());
                    String bid = scanner.nextLine();
                    try {
                        boolean accepted = round.setBid(Integer.parseInt(bid), round.getPlayers().get(upNow));
                        if (accepted) {
                            wipe();
                            break;
                        } else {
                            write("Enter a valid bid you cheater");
                        }
                    } catch (Exception e) {
                        System.out.println("Enter a valid bid!");
                    }
                    // play

                }

            }

        }
    }

    public static void write(String message) {
        System.out.println(message);
    }

    public static void wipe() {
        for (int i = 0; i < 50; i++) {
            write("*********************************************** ***************************************************");
            write("*********************************************** ***************************************************");
            System.out.println();
        }
    }

    // show them the hand, ask them to pick a card, put that in trick.playCard();
    public int pickACard(){

        return 0;
    }

}
