package com.techelevator.deck;

import com.techelevator.deck.games.FortyFives;
import com.techelevator.deck.games.KingsInTheCorner;
import com.techelevator.deck.games.Playable;
import com.techelevator.deck.games.War;
import com.techelevator.deck.model.Deck;
import com.techelevator.deck.model.Hand;
import com.techelevator.deck.services.AuthenticationService;
import com.techelevator.deck.services.ConsoleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService();
    List<String> victoryPhrases = new ArrayList<>(Arrays.asList("Ergh... Good Game", "You Won, Happy!?", "GAH! If I had a virtual table, I'd have flipped it over by now!", "I had such bad cards! Go Again!", "If it weren't for the virtual deck, I'd say you cheated!", "How'd you beat me?!?", "Hm... Nice Job... For a human...."));
    List<String> lossPhrases = new ArrayList<>(Arrays.asList("Better Luck Next Time...", "HA, Take that!", "Were You Even Trying?", "That was pitiful...", "I'd Say Nice Try, but...", "Yikes! You lost big time!", "Um... Keep Practicing?", "AI: 1, Meat-For-Brains: 0"));
    String previous = "Which game do you want to lose to this time?";
    Deck deck = new Deck();
    Hand hand = new Hand();

    public static void main(String[] args) {
        App app = new App();
        app.run();

    }

    private void run() {

        int menuSelection = -1;

        List<String> initial = new ArrayList<>();
        initial.add("----Deck Simulator----");
        initial.add("1: Mess with Deck");
        initial.add("2: Play a Game");
        initial.add("0: Exit");
        while (menuSelection != 0) {
            consoleService.printMainMenu(initial);
            System.out.print("Please choose an option: ");
            menuSelection = consoleService.promptForMenuSelection();
            if (menuSelection == 1) {
                messWithDeck();
            } else if (menuSelection == 2) {
                gameSelector();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
            }
        }
    }

    private void messWithDeck(){
        int menuSelection = -1;
        List<String> options = new ArrayList<>();
        options.add("----Deck Simulator----");
        options.add("1: Display the Ordered Deck");
        options.add("2: Display the Shuffled Deck");
        options.add("3: Shuffle the Deck");
        options.add("4: Use a Fresh Deck");
        options.add("5: Deal Me a Card");
        options.add("6: Deal Me a New Hand (5)");
        options.add("7: Show Me my Hand");
        options.add("8: Play a Card");
        options.add("0: Exit");
        while (menuSelection != 0) {
            consoleService.printMainMenu(options);
            System.out.print("Please choose an option: ");
            menuSelection = consoleService.promptForMenuSelection();
            if (menuSelection == 1) {
                System.out.println(deck.toString(false));
            } else if (menuSelection == 2) {
                System.out.println(deck.toString(true));
            } else if (menuSelection == 3) {
                deck.shuffleExisting(2);
                System.out.println(deck.toString(true));
            } else if (menuSelection == 4) {
                deck = new Deck();
                hand = new Hand();
                System.out.println("Fresh Deck is Now in Play!");
            } else if (menuSelection == 5) {
                System.out.println("You just pulled the " + hand.pullCard(deck.pullCard()));
            } else if (menuSelection == 6) {
                hand = new Hand();
                for (int i = 0; i < 5; i++) {
                    hand.pullCard(deck.pullCard());
                }
            } else if (menuSelection == 7) {
                System.out.println(hand);
            } else if (menuSelection == 8) {
                System.out.println("You played the " + hand.playCard());
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
            }
        }

    }

    private void gameSelector() {
        Deck deck = new Deck();
        Hand hand = new Hand();
        int menuSelection = -1;

        List<String> gameChoices = new ArrayList<>();
        gameChoices.add("1: Forty Fives");
        gameChoices.add("2: Kings in the Corner");
        gameChoices.add("3: War");
        gameChoices.add("0: Exit");

        while (menuSelection != 0) {

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n----Card Game Choices----                " + previous);
            consoleService.printMainMenu(gameChoices);
            System.out.print("Please choose your game: ");
            menuSelection = consoleService.promptForMenuSelection();
            Playable game = null;
            if (menuSelection == 1) {
                game = new FortyFives();
            } else if (menuSelection == 2) {
                game = new KingsInTheCorner();
            } else if (menuSelection == 3) {
                game = new War();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
            }
            if (game != null) {
                System.out.println(game.getIntro() + "\n\n\n\n\n");
                if (game.run()) {
                    previous = victoryPhrases.get((int) (Math.random() * victoryPhrases.size()));
                } else {
                    previous = lossPhrases.get((int) (Math.random() * lossPhrases.size()));
                }
            }

        }
    }

//    private void handleListAllAuctions() {
//        Auction[] auctions = auctionService.getAllAuctions();
//        printAuctionsOrError(auctions);
//    }

//    private void printAuctionsOrError(Auction[] auctions) {
//        if (auctions != null) {
//            consoleService.printAuctions(auctions);
//        } else {
//            consoleService.printErrorMessage();
//        }
//    }

//    private void handleShowAuctionDetails() {
//        Auction[] auctions = auctionService.getAllAuctions();
//        if (auctions != null) {
//            consoleService.printAuctionMenu(auctions);
//            int auctionId = consoleService.promptForMenuSelection();
//            if (auctionId > 0) {
//                Auction auction = auctionService.getAuction(auctionId);
//                if (auction != null) {
//                    consoleService.printAuction(auction);
//                } else {
//                    consoleService.printErrorMessage();
//                }
//            }
//        } else {
//            consoleService.printErrorMessage();
//        }
//    }

//    private void handleFindAuctionsByTitle() {
//        String title = consoleService.promptForAuctionTitle();
//        if (title != null) {
//            Auction[] auctions = auctionService.getAuctionsMatchingTitle(title);
//            printAuctionsOrError(auctions);
//        }
//    }

//    private void handleFindAuctionsByPrice() {
//        double price = consoleService.promptForAuctionPrice();
//        if (price > 0) {
//            Auction[] auctions = auctionService.getAuctionsAtOrBelowPrice(price);
//            printAuctionsOrError(auctions);
//        }
//    }

//    private void handleAddAuction() {
//        Auction auctionEnteredByUser = consoleService.promptForAuctionData();
//        Auction auctionFromApi = auctionService.add(auctionEnteredByUser);
//        if (auctionFromApi == null) {
//            consoleService.printErrorMessage();
//        }
//    }

//    private void handleUpdateAuction() {
//        Auction[] auctions = auctionService.getAllAuctions();
//        if (auctions != null) {
//            consoleService.printAuctionMenu(auctions);
//            int auctionId =  consoleService.promptForMenuSelection();
//            if (auctionId > 0) {
//                Auction existingAuction = auctionService.getAuction(auctionId);
//                if (existingAuction != null) {
//                    Auction auctionEnteredByUser = consoleService.promptForAuctionData(existingAuction);
//                    if (!auctionService.update(auctionEnteredByUser)) {
//                        consoleService.printErrorMessage();
//                    }
//                } else {
//                    consoleService.printErrorMessage();
//                }
//            }
//        } else {
//            consoleService.printErrorMessage();
//        }
//    }
//
//    private void handleDeleteAuction() {
//        Auction[] auctions = auctionService.getAllAuctions();
//        if (auctions != null) {
//            consoleService.printAuctionMenu(auctions);
//            int auctionId = consoleService.promptForMenuSelection();
//            if (auctionId > 0) {
//                if (!auctionService.delete(auctionId)) {
//                    consoleService.printErrorMessage();
//                }
//            }
//        } else {
//            consoleService.printErrorMessage();
//        }
//    }

    private void handleLogin() {
        String username = consoleService.promptForString("Username: ");
        String password = consoleService.promptForString("Password: ");
        String token = authenticationService.login(username, password);
        if (token != null) {
//            auctionService.setAuthToken(token);
        } else {
            consoleService.printErrorMessage();
        }
    }


}
























