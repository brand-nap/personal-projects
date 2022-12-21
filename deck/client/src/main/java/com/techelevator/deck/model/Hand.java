package com.techelevator.deck.model;

import com.techelevator.deck.services.ConsoleService;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Card> hand = new ArrayList<>();

    public Hand(Deck deck) {
        for (int i = 0; i < 5; i++) {
            this.hand.add(deck.pullCard());
        }
    }

    public Hand() {}

    public Card pullCard(Card card){
        hand.add(card);
        return card;
    }

    public Card playCard(){
        int menuSelection = 100;
        while (menuSelection != 0) {
            ConsoleService consoleService = new ConsoleService();
            System.out.println(hand);
            menuSelection = consoleService.promptForMenuSelection();
            try {
                return hand.remove(menuSelection-1);
            }catch(Exception e){
                    System.out.println("Please Pick within Your Hand");
            }
        }
        return null;
    }

    public int size(){
        return this.hand.size();
    }
    public Card playTopCard(){
        if(size()>0){
            return this.hand.remove(0);
        }
        return null;
    }
    public boolean contains(int value, String suit){
        for(int i = 0; i<hand.size(); i++){
            if(hand.get(i).getValue()==value && hand.get(i).getSuit().equals(suit)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String finalReturn = "                              ";
        int cardNum = 1;
        int enter = 0;
        String str = "";
        for (Card card: hand) {
            if(enter<(cardNum-1)/5){
                finalReturn+="\n                              ";
                enter++;
            }
            str += "" + cardNum +". "+ card;
            while(str.length()<30){
                str+=" ";
            }
            finalReturn+=str;
            cardNum++;
            str="";
        }
        return finalReturn;
    }
}
