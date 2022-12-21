package com.techelevator.deck.games;

import com.techelevator.deck.model.Deck;
import com.techelevator.deck.services.ConsoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KingsInTheCorner implements Playable{
    Deck deck = new Deck();
    List<String> rules = new ArrayList<>();
    List<String> playOrNot = new ArrayList<>();
    ConsoleService consoleService = new ConsoleService();
    Scanner scanner = new Scanner(System.in);
    final String intro = "\n\n\n\n\n\n\n\n\n\n\n" +
            "                                               Welcome to Kings in the Corner!                                                \n" +
            "                                    A collaborative Solitaire spin-off and family classic.                                    \n" +
            "                                                                                                                              \n";


    @Override
    public boolean play() {
        return false;
    }

    @Override
    public void rules() {

    }
    @Override
    public String getIntro() {
        return intro;
    }
}
