package com.techelevator.deck.games;

import com.techelevator.deck.model.Deck;
import com.techelevator.deck.services.ConsoleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

public class FortyFives implements Playable{

    Deck deck = new Deck();
    ConsoleService consoleService = new ConsoleService();
    Scanner scanner = new Scanner(System.in);



    String intro = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                                       Welcome to Forty Fives!                                                \n" +
            "                                A complex New England classic built off strategy, risk, and intuition.                        \n" +
            "                                                                                                                              \n";
    final String ROUNDS = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                                            ---ROUNDS---                                                      \n" +
            "                                                                                                                              \n" +
            "      Setting the Stage...                                                                                                    \n" +
            "         The Dealer passes 5 cards to each Player and 3 cards to *the Kitty*                                                  \n" +
            "         Going clockwise, each Player sets their *bid*.                                                                       \n" +
            "           if all Players pass, the Dealer must bid at a default 15.                                                          \n" +
            "         The Bidder must pick their suit before seeing the Kitty.                                                             \n" +
            "         Players may then toss any unwanted cards.                                                                            \n" +
            "         The Dealer finally replenishes all Players to 5 cards.                                                               \n" +
            "           if a Player tosses all 5, expose 5th card they received.                                                           \n" +
            "                                                                                                                              \n" +
            "                                                                                                          Playing the Cards...\n" +
            "                                                     There are 5 plays in each round (one play for each card in your hand).   \n" +
            "                                                                             If it's the first play, the Bidder goes first.   \n" +
            "                                                                     Otherwise, the winner of the previous play goes first.   \n" +
            "                                                                       Each player then presents a card in clockwise order.   \n" +
            "                                                          *Read the Strategy section to learn how players pick their cards.*  \n" +
            "      Awarding Points...                                                                                                      \n" +
            "         Each round won is worth 5 points.                                                                                    \n" +
            "         The round won with High Card is worth 10 points.                                                                     \n" +
            "        *Read the Card Ranking section for how to determine which is High Card.*                                              \n" +
            "         This totals to 30 possible points.                                                                                   \n" +
            "         If the Bidder did not make their bid, they lose the amount of points their bid is worth.                             \n" +
            "                                                                                                                              \n" +
            "                                                    [Press Enter When Finished]                                                 ";
    final String RANKING = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                                        ---CARD RANKINGS---                                                   \n" +
            "                              each round, a suit is chosen, said suit will always outrank offsuits                            \n" +
            "                                             the ♥ace of hearts♥ is the only exception                                        \n" +
            "                                                                                                                              \n" +
            "                                         ---------------------IN SUIT---------------------                                    \n" +
            "      Red Suits Go As Follows:                                                                      Black Suits Go As Follows:\n" +
            "       5, J, A♥, A, K, Q, 10, 9, 8, 7, 6, 4, 3, 2, offsuits              5, J, A♥, A, K, Q, 2, 3, 4, 6, 7, 8, 9, 10, offsuits \n" +
            "                                                                                                                              \n" +
            "                                High Card is simply the highest in-suit card played during a round                            \n" +
            "                                         High Card is also worth 10 points rather than 5                                      \n" +
            "                                                                                                                              \n" +
            "                                        ---------------------OFFSUITS---------------------                                    \n" +
            "                                   Red Suits Go As Follows:             Black Suits Go As Follows:                            \n" +
            "                                              K, Q, J, 10-2             K, Q, J, A-10                                         \n" +
            "                                                                                                                              \n" +
            "                                                                                                                              \n" +
            "                                             [Select 1 to Exit, 2 for Reneging Rules]                                           ";

    final String BIDDING = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                                           ---BIDDING---                                                      \n" +
            "                                                                                                                              \n" +
            "      What is it...                                                                                                           \n" +
            "         Bidding is when a Player essentially places a bet                                                                    \n" +
            "         This bet states that if the Player were to pick the suit and receive the Kitty, they could achieve X points          \n" +
            "         Bids may be for 15, 20, 25, or 30 points.                                                                            \n" +
            "                                                                                                                              \n" +
            "      Why bid?                                                                                                                \n" +
            "         Bidding allows you to pick the suit, receive the kitty, and play first                                               \n" +
            "         - By picking suit, you ensure that your high cards (5, J, etc) retain their value                                    \n" +
            "         - Receiving the kitty allows you to *initially* shuffle through 8 cards instead of 5                                 \n" +
            "         - Playing first gives the opportunity to force others to play in suit if desired                                     \n" +
            "                                                                                                                              \n" +
            "      What's the catch?                                                                                                       \n" +
            "         If a player fails to make their bid, they lose X points                                                              \n" +
            "         This is called \"being set\" or \"setting\" someone                                                                  \n" +
            "                                                                                                                              \n" +
            "     EXPLICIT RULES:                                                                                                          \n" +
            "         You must only bid higher than any previous bids made                                                                 \n" +
            "         You may not bid below 15                                                                                             \n" +
            "         If all Players pass, the Dealer must bid a minimum of 15                                                             \n" +
            "         The Bidder must play first                                                                                           \n" +
            "         The Bidder must pick up the kitty and discard down to 5 or less                                                      \n" +
            "         BIDDER GOES OUT:                                                                                                     \n" +
            "         If 2 or more players would win after the final round, and any are the bidder, the bidder wins by default             \n" +
            "                                                                                                                              \n" +
            "      UNSOLICITED TIPS:                                                                                                       \n" +
            "         Don't bid 20 without a high card                                                                                     \n" +
            "         Don't bid 30, ever.                                                                                                  \n" +
            "         Don't be shy! If you don't bid you'll statistically lose                                                             \n" +
            "         When choosing between many cards in one suit, or 1-2 high cards, go with the high cards                              \n" +
            "         Sometimes, you'd make more progress setting your opponent than you would making a bid                                \n" +
            "                                                                                                                              \n" +
            "                                               [Select 1 to Exit, 2 for Key Terms]                                              ";

    final String KEY = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                                        ---KEY TERMS---                                                       \n" +
            "                                                                                                                              \n" +
            "                                                          -The Kitty-                                                         \n" +
            "                                      The Kitty is 3 cards dealt at any point to the center                                   \n" +
            "                                   The Bidder receives these cards after they declare the suit                                \n" +
            "                  They can then discard from their 8 cards down to 5 or less before being replenished to 5                    \n" +
            "                                                                                                                              \n" +
            "                                                             -Set-                                                            \n" +
            "                                  Set is the term for when a Player fails to make their bid                                   \n" +
            "                                           When someone is set, they lose X points                                            \n" +
            "                                                                                                                              \n" +
            "                                                          -High Card-                                                         \n" +
            "                            High Card refers to the highest card that had been played within a round                          \n" +
            "                                   The play won with High Card is worth 10 pts rather than 5                                  \n" +
            "                                                                                                                              \n" +
            "                                                           -Offsuit-                                                          \n" +
            "                              Offsuit refers to any suit other than the suit chosen by the Bidder                             \n" +
            "                          These suits are worth less than the suit chosen and base off a unique ranking                       \n" +
            "                                                                                                                              \n" +
            "                                                          -Reneging-                                                          \n" +
            "                      Reneging is when a Player purposely loses a play by withholding a higher value card                     \n" +
            "                                                                                                                              \n" +
            "                                                  [Press Enter When Finished]                                                   ";

    final String RENEGING = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                                        ---RENEGING---                                                        \n" +
            "                      Reneging is when a Player purposely loses a play by withholding a higher value card                     \n" +
            "                                                                                                                              \n" +
            "      So... there's a catch to 45's. Sometimes, you're forced to play certain cards. Even when you don't want to.             \n" +
            "      This guide is meant to outline what you can withhold, and what you can't                                                \n" +
            "                                                                                                                              \n" +
            "                                          YOU MAY ALWAYS PLAY A HIGHER VALUE CARD                                             \n" +
            "                                                                                                                              \n" +
            "      If the first card played is within suit and you have a card in suit                                                     \n" +
            "         You must play within suit                                                                                            \n" +
            "      If the first card played is an offsuit and you have a card within said offsuit                                          \n" +
            "         You must play within said offsuit before playing a different offsuit                                                 \n" +
            "                                                                                                                              \n" +
            "                                         when the first card played is within suit                                            \n" +
            "                                       YOU ARE NOT REQUIRED TO PLAY THE 5, J, nor A♥                                          \n" +
            "                                                                                                                              \n" +
            "                                         unless said first card is higher ranking                                             \n" +
            "                                                                                                                              \n" +
            "      Examples:                                                                                                               \n" +
            "      suit is clubs♣ | first card is 5♣ | hand: J♣, 2♦, 8♦                                                                   \n" +
            "      I must play my J♣                                                                                                       \n" +
            "      explanation: 5 of Clubs outranks Jack of Clubs                                                                          \n" +
            "                                                                                                                              \n" +
            "      suit is clubs♣ | first card is J♣ | hand: 5♣, 2♦, 8♦                                                                   \n" +
            "      I can play any card I'd like                                                                                            \n" +
            "      explanation: 5 of Clubs outranks Jack of Clubs                                                                          \n" +
            "                                                                                                                              \n" +
            "      suit is clubs♣ | first card is 4♣ | hand: 3♣, 2♦, 8♣                                                                   \n" +
            "      I must play my 3♣ or 8♣                                                                                                \n" +
            "      explanation: 4 of Clubs is within suit, and I have cards within suit                                                    \n" +
            "                                                                                                                              \n" +
            "      suit is clubs | first card is 4♦ | hand: J♣, 2♦, 8♦, 4♥                                                                \n" +
            "      I must play my 2♦, 8♦, or J♣                                                                                            \n" +
            "      explanation: I must play my diamond offsuits higher ranking card since a diamond led.                                   \n" +
            "                                                                                                                              \n" +
            "                                                  [Press Enter When Finished]                                                   ";
    List<String> rulesInDepth = new ArrayList<>(Arrays.asList(ROUNDS, RANKING, BIDDING, RENEGING, KEY));
    List<String> rulesTitles = new ArrayList<>(Arrays.asList("1: Rounds","2: Card Rankings", "3: Bidding", "4: Reneging", "5: Key Terms","0: Exit"));
    @Override
    public String getIntro() {
        return intro;
    }
    @Override
    public boolean play(){
        return false;
    }

    @Override
    public void rules(){
        int ruleSelection = -1;
        while(ruleSelection!=0) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            if(ruleSelection==-1) {
                consoleService.printMainMenu(rulesTitles);
                System.out.print("Please select: ");
                ruleSelection = consoleService.promptForMenuSelection();
            }
            //handles the printing of all rules, regardless of how many there may be. Previous way was too rigid imo.
            System.out.println((ruleSelection-1<rulesInDepth.size()&&ruleSelection>0)?rulesInDepth.get(ruleSelection-1):(ruleSelection ==0)?"":"Invalid Selection");

            //allows a player to have time to read before moving to the next selection
            int temp = consoleService.promptForMenuSelection();

            //handles my two special case scenarios in 45's
            if(temp==2&&(ruleSelection==2||ruleSelection==3)){
                ruleSelection = (temp==2)? ruleSelection+2 : -1;
            }else if(ruleSelection!=0){
                ruleSelection=-1;
            }
        }
    }


}
