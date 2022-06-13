package kitmorgan;

import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

public class OhHellUserInterface {
    //Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

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
                } else if (player.playerName.length() >= 1 && player.playerName.length() <= 15) {
                    player.playerName = name;
                    enteredNames.add(name);
                    break;
                } else {
                    write("Pick a valid name. Cannot be zero length or longer than 15 characters.");
                }
            }
        }
        wipe();
        write("Input accepted, starting game for: ");
        write(toStringPlayers(ohHell));
        do{ //do as long as a round will be created.
            // create a round
            round = ohHell.createRound();

            System.out.printf(round.dealer.getPlayerName() + " is dealing! Press enter to deal...");
            scanner.nextLine();
            write("");
            write("READ ME: '" + ANSI_RED + round.getTrump().toString() + " is the trump card" + ANSI_RESET + ", lets start bidding.");

            System.out.printf("%s is the first to bid', pass the computer to them.\n", ohHell.players.get((ohHell.dealerIndex + 1) % (ohHell.getNumberOfPlayers() - 1)).playerName);
            write("press enter when ready (hand will be exposed)");
            scanner.nextLine();
            wipe();
            // works
            while (ohHell.hasNextToAct()) {
                write(ANSI_GREEN + ohHell.getUpNow().getPlayerName() + "'s Turn" + ANSI_RESET);
                write("Trump: " + round.getTrump().toString());
                write("Current bid is at: " + round.getBidSoFar());
                write("Your hand: " + ohHell.getUpNow().handToString());
                if (ohHell.getUpNow().isDealer()) {
                    if (round.isOverBid()) {
                        write("Alright dealer we're overbid, lucky you.");
                    } else {
                        System.out.println("Dealer is up, you can't bid: " + round.getInvalidDealerBid());
                    }
                }
                while (true) {
                    System.out.printf("%s, enter your bid: ", ohHell.getUpNow().getPlayerName());
                    String bid = scanner.nextLine();
                    try {
                        if (round.setBid(Integer.parseInt(bid), ohHell.getUpNow())){
                            wipe();
                            //what if every time the bid (setBid) has acted = true;
                            break;
                        } else {
                            write("Enter a valid bid you cheater");
                        }
                    } catch (Exception e) {
                        System.out.println("Enter a valid bid!");
                    }
                }
            }
            // bids set, now for the tricks
            wipe();
            write("Bids are set, lets play. Pass the computer to your left....");
            scanner.nextLine();
            // play
            //TODO: need to rework the logic and counter
            do{
             //just added this 9:57 6/10/22
                round.createTrick();
                while (ohHell.hasNextToAct()) {  //TODO: while(trick.hasNext)
                    write("Trump: " + round.getTrump().toString());
                    write("Your bid: " + round.roundInfoMap.get(ohHell.getUpNow()).getBid());
                    write(ohHell.getUpNow().getPlayerName() + "'s hand:" + ohHell.getUpNow().handToString());
                    while (true) {
                        write("Play a card (#): ");
                        String input = scanner.nextLine();
                        try {
                            trick.playCard(ohHell.getUpNow(), Integer.parseInt(input) - 1);
                            break;
                        } catch (Exception e) {
                            write("Enter a valid card number.");
                        }
                    }
                    wipe();
                    write(trick.playedCardsToString());
                }
            }while (round.hasNextTrick());
            //round over, score tallied
        }while (ohHell.hasNextRound());

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
    public int pickACard() {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        while (true) {
            write("pick a card (#): ");
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                write("write a valid number");
            }
        }
        return input;
    }

    public static String toStringPlayers(OhHell ohHell) {
        StringBuilder answer = new StringBuilder("[");
        int counter = 1;
        for (Player player : ohHell.players) {
            answer.append(player.getPlayerName());
            if (counter < ohHell.getNumberOfPlayers()) {
                answer.append(", ");
            }
            counter++;
        }
        answer.append("]");
        return answer.toString();
    }
}
