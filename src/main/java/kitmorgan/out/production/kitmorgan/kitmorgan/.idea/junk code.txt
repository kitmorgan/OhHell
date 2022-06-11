//package kitmorgan;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class OhHellUI {
//
//
//
//
//    /** asks the user for their bid, uses different logic if they are the dealer */
//    public void askBid(List<String> validBids, int currentBids, int round){
//        Scanner scanner = new Scanner(System.in);
//        int anythingBut = round - currentBids;
//        String input;
//        int bid;
//        boolean test = false;
//        System.out.println(this.getPlayerName() + "'s hand: " + this.getHand());
//        if (this.isDealer()){
//            if (currentBids > round) {
//                System.out.print("Alright dealer, lucky you looks like we're overbid, enter your bid: ");
//            } else {
//                System.out.printf("Alright dealer, it's your bid, anything but %s: ", anythingBut);
//            }do{
//                input = scanner.nextLine();
//                if(!validBids.contains(input) || String.valueOf(anythingBut).equalsIgnoreCase(input)) {
//                    System.out.print("You can't bid that. Bid again: ");
//                }else{
//                    this.bid = Integer.parseInt(input);
//                    test = true;
//                }
//            }while(!test);
//        }else{
//            do {
//                System.out.print(this.getPlayerName() + ", enter your bid: ");
//                input = scanner.nextLine();
//                if (validBids.contains(input)){
//                    this.bid = Integer.parseInt(input);
//                    test = true;
//                }
//            }while(!test);
//        }
//    }
//    // asks the user to play a card returns a card
//    public Card playCard(){
//        Scanner scanner = new Scanner(System.in);
//        String cardPickNumber = "";
//        Card chosenCard;
//        System.out.printf("%s's hand: ", this.getPlayerName());
//        System.out.print(this.getHand());
//        System.out.println("");
//        do {
//            System.out.printf("Play a card (1 - %s): ", this.getHand().size());
//            cardPickNumber = scanner.nextLine();
//
//        } while (!cardPickNumber.matches("[1-9]"));
//        chosenCard = hand.get(Integer.parseInt(cardPickNumber) - 1);
//        hand.remove(Integer.parseInt(cardPickNumber) - 1);
//        return chosenCard;
//    }
//
//}
