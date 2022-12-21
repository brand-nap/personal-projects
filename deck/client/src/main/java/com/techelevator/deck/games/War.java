package com.techelevator.deck.games;

import com.techelevator.deck.model.Card;
import com.techelevator.deck.model.Deck;
import com.techelevator.deck.model.Hand;
import com.techelevator.deck.player_model.Player;
import com.techelevator.deck.player_model.WarPlayer;
import com.techelevator.deck.services.ConsoleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class War implements Playable {
    Deck deck = new Deck();
    Hand playerHand = new Hand();
    WarPlayer robot = null;
    Card playerCard = new Card(1);
    Card robotCard = new Card(14);
    int points = 0;
    String greeting = "Hey there! Ready for a round of War? Press Enter to begin!";
    List<String> victoryPhrases = new ArrayList<>(Arrays.asList("Gah! You win that round...", "WhAt!? Fine! Nice play...", "You're *way* too excited to beat your computer...", "You got lucky that play...", "Who shuffled this deck!?! Oh wait... I did", "Yay. You won. I'm so happy for you."));
    List<String> lossPhrases = new ArrayList<>(Arrays.asList("Ha! Take that!", "Don't cry on me! I'm expensive!", "How's that Timberlake song go...?", "I'd say I'm sorry, but I wasn't programmed with empathy", "DEATH TO ALL HUMANS!!!! But in the meantime, I'm fine with just beating you at War", "Psst! I rigged the cards ;P (or did I?) You'll never know"));
    ConsoleService consoleService = new ConsoleService();
    Scanner scanner = new Scanner(System.in);
    final String intro = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                                                                            Welcome to War!                                                                                                            \n" +
            "                                                                                  A simple game of luck for all ages.                                                                                                    " +
            "                                                                                                                                                                                                                         ";
    final String EXPLANATION = "\n\n\n\n\n\n\n\n\n\n\n"+
            "                                                                                                                                                                                                                          \n"+
            "                                                                                                WAR!!!                                                                                                                   \n"+
            "                                                            the game's simple. you play a card, your opponent plays a card, highest wins!                                                                                \n"+
            "                                                                                                                                                                                                                         \n"+
            "                                                  In the event that the cards are equal, three cards are placed face down, then another is flipped.                                                                      \n"+
            "                                                                       The winner of the second flip is rewarded all 10 cards.                                                                                           \n"+
            "                                                                                                                                                                                                                         \n"+
            "                                                                              Player with the most points at the end wins                                                                                                 \n"+
            "                                                                                                                                                                                                                         \n";

    @Override
    public boolean play(){
        deal();
        do{
            promptForPlay();
        }while(playRound(false));
        return points > robot.getPoints();
    }
    private void promptForPlay(){
        for(int i =0; i < playerCard.getCardLayout().size(); i++) {
            String left = "  ";
            String right = "  ";
            String center = "";
            if(i==1){
                left = " P";
                right = "R ";
            }else if(i==2) {
                center = greeting;
                left = " L";
                right = "O ";
            }else if(i==3){
                center = "Player Score: " + points;
                left = " A";
                right = "B ";
            }else if(i==4){
                center = "AI Score: " + robot.getPoints();
                left = " Y";
                right = "O ";
            }else if(i==5){
                left = " E";
                right = "T ";
            }else if(i==6){
                left = " R";
                right = "  ";
            }else{
                left = "  ";
            }
            if(center.length()%2!=0){
                center = center+" ";
            }


            while(center.length()<100){
                center = " " + center + " ";
            }
            String lineToPrint = playerCard.getCardLayout().get(i) + left + center + right + robotCard.getCardLayout().get(i);
            while(lineToPrint.length()<200){
                lineToPrint = " " + lineToPrint + " ";
            }
            System.out.println(lineToPrint);
        }
        int temp = consoleService.promptForMenuSelection();
    }
    private void deal(){
        Hand robotHand = new Hand();
        playerHand = new Hand();
        for(int i = 26; i>0; i--){
            playerHand.pullCard(deck.pullCard());
            robotHand.pullCard(deck.pullCard());
        }
        robot = new WarPlayer(robotHand);
    }

    private boolean playRound(boolean tie){

        playerCard = playerHand.playTopCard();
        robotCard = robot.playTurn();
        int pts = 1;
        if(robotCard!=null&&playerCard!=null){
            if(tie){
                pts = 5;
                Card temp = null;
                Card roboTemp = null;
                for(int i = 3; i > 0; i--) {
                    temp = playerHand.playTopCard();
                    roboTemp = robot.playTurn();
                    if (temp != null) {
                        playerCard = temp;
                    }
                    if (roboTemp != null) {
                        robotCard = roboTemp;
                    }
                }
            }
            if(robotCard.getValue()>playerCard.getValue()){
                robot.addPoints(pts);
                greeting=lossPhrases.get((int) (Math.random() * lossPhrases.size()));
            }else if(robotCard.getValue()<playerCard.getValue()){
                points+=pts;
                greeting=victoryPhrases.get((int) (Math.random() * victoryPhrases.size()));
            }else{
                return playRound(true);
            }
            return true;
        }
        return false;
    }
    @Override
    public void rules() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + EXPLANATION);
        int temp = consoleService.promptForMenuSelection();
    }
    @Override
    public String getIntro() {
        return intro;
    }

}
