package kitmorgan;

import java.util.Scanner;

public class OhHellUserInterface {
    public static void main(String[] args) {
        OhHell ohHell = null;
        Round round = null;
        Trick trick = null;
        Scanner scanner = new Scanner(System.in);
        // get user input on how many players
        while(true) {
            try {
                write("Welcome to Oh Hell, how many players? (3 - 7): ");
                String input = scanner.nextLine();
                ohHell = new OhHell(Integer.parseInt(input));
                break;
            } catch (Exception e) {
                write("Enter a valid integer 3 - 7");
            }
        }
        // start the game
        for(int i = 1; i < ohHell.getNumberOfRounds(); i++){
            Boolean hasTrump = true;
            if(i < ohHell.getNumberOfRounds() - 1) {
                hasTrump = true;
                round = ohHell.createRound(i,hasTrump);
            }else {
                hasTrump = false;
                round = ohHell.createRound(i, hasTrump);
            }round.deal(i,hasTrump);
            System.out.println(round.getPlayers());
        }
    }

    public static void write(String message){
        System.out.print(message);
    }
    public static void wipe(){
        for(int i = 0; i < 50; i++){
            write("*********************************************** ***************************************************");
            write("*********************************************** ***************************************************");
            System.out.println();
        }
    }

}
