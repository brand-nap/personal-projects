package com.techelevator.deck.games;

import com.techelevator.deck.model.Deck;
import com.techelevator.deck.services.ConsoleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public interface Playable {
    Deck deck = new Deck();
    List<String> rules = new ArrayList<>();
    List<String> playOrNot = new ArrayList<>(Arrays.asList("1: Play Game","2: Show Rules","0: Exit")) ;
    ConsoleService consoleService = new ConsoleService();
    String INTRO = "";
    Scanner scanner = new Scanner(System.in);

    public boolean play();
    public void rules();

    public default boolean run(){
        int playSelection = -1;
        while(playSelection!=0) {
            consoleService.printMainMenu(playOrNot);
            playSelection = consoleService.promptForMenuSelection();
            if (playSelection == 1) {
                return play();
            } else if (playSelection == 2) {
                rules();
            } else if (playSelection != 0) {
                System.out.println("Invalid Selection");
            }
        }
        return false;
    }

    String getIntro();
}
