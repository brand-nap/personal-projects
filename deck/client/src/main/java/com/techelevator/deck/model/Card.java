package com.techelevator.deck.model;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private int id;
    private int value;
    private String suit;
    private boolean isRoyal;
    private char suitChar;
    private String imageUrl;
    private String title;
    private String valueTitle;
    private String letter;
    private List<String> cardLayout = new ArrayList<>();

    public Card() {
    }

    public Card(String imageUrl, int id) {
        this.id = id;
        this.value = (id%13==0) ? 13 : id%13 ; //will do mod 13 unless a king that way king stays worth 13 instead of 0
        this.valueTitle = getValueTitle(this.value);
        this.suit = (id<14)? "Clubs" : (id<27)? "Diamonds" : (id<40)? "Hearts" : "Spades";
        this.suitChar = (id<14)? '♣' : (id<27)? '♦' : (id<40)? '♥' : '♠';
        this.isRoyal = value>10;
        this.imageUrl = imageUrl;
        if(this.value!=10) {
            cardLayout.add(" _____________ ");
            cardLayout.add("|" + this.letter + "            |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|      " + this.suitChar + "      |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|____________" + this.letter + "|");
        }else{
            cardLayout.add(" _____________ ");
            cardLayout.add("|" + this.letter + "           |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|      " + this.suitChar + "      |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|___________" + this.letter + "|");
        }
        }
    public Card(int id) {
        this.id = id;
        this.value = (id%13==0) ? 13 : id%13 ; //will do mod 13 unless a king that way king stays worth 13 instead of 0
        this.valueTitle = getValueTitle(this.value);
        this.suit = (id<14)? "Clubs" : (id<27)? "Diamonds" : (id<40)? "Hearts" : "Spades";
        this.suitChar = (id<14)? '♣' : (id<27)? '♦' : (id<40)? '♥' : '♠';
        this.isRoyal = value>10;
        this.title = "" + this.suitChar + this.valueTitle + " of " + this.suit + this.suitChar;
        if(this.value!=10) {
            cardLayout.add(" _____________ ");
            cardLayout.add("|" + this.letter + "            |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|      " + this.suitChar + "      |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|____________" + this.letter + "|");
        }else{
            cardLayout.add(" _____________ ");
            cardLayout.add("|" + this.letter + "           |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|      " + this.suitChar + "      |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|___________" + this.letter + "|");
        }
    }


    public Card(int value, String suit, String imageUrl) {
        this.value = value;
        this.suit = suit;
        this.id = value + 13*((suit.equals("Clubs"))? 0 : (suit.equals("Diamonds"))? 1 : (suit.equals("Hearts"))? 2 : 3 );
        setCard(this.id, imageUrl);
    }
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
        this.id = value + 13*((suit.equals("Clubs"))? 0 : (suit.equals("Diamonds"))? 1 : (suit.equals("Hearts"))? 2 : 3 );
        setCard(this.id);

    }

    private void setCard(int id, String imageUrl) {
        setCard(id);
        this.imageUrl = imageUrl;
    }
    private void setCard(int id) {
        this.id = id;
        this.value = (id%13==0) ? 13 : id%13 ; //will do mod 13 unless a king that way king stays worth 13 instead of 0
        this.valueTitle = getValueTitle(this.value);
        this.suit = (id<14)? "Clubs" : (id<27)? "Diamonds" : (id<40)? "Hearts" : "Spades";
        this.suitChar = (id<14)? '♣' : (id<27)? '♦' : (id<40)? '♥' : '♠';
        this.isRoyal = value>10;
        this.title = "" + this.suitChar + this.valueTitle + " of " + this.suit + this.suitChar;
        if(this.value!=10) {
            cardLayout.add(" _____________ ");
            cardLayout.add("|" + this.letter + "            |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|      " + this.suitChar + "      |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|____________" + this.letter + "|");
        }else{
            cardLayout.add(" _____________ ");
            cardLayout.add("|" + this.letter + "           |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|      " + this.suitChar + "      |");
            cardLayout.add("|             |");
            cardLayout.add("|             |");
            cardLayout.add("|___________" + this.letter + "|");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public boolean isRoyal() {
        return isRoyal;
    }

    public void setRoyal(boolean royal) {
        this.isRoyal = royal;
    }

    public char getSuitChar() {
        return suitChar;
    }

    public void setSuitChar(char suitChar) {
        this.suitChar = suitChar;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public List<String> getCardLayout(){


        return cardLayout;
    }

    public String getValueTitle(){
        return valueTitle;
    }
    public String getValueTitle(int value){
        String finalTitle = "";
        if(value == 1){
            finalTitle="Ace";
            this.letter = "A";
        }else if(value ==2){
            finalTitle="Two";
            this.letter = "2";
        }else if(value ==3){
            finalTitle="Three";
            this.letter = "3";
        }else if(value ==4){
            finalTitle="Four";
            this.letter = "4";
        }else if(value ==5){
            finalTitle="Five";
            this.letter = "5";
        }else if(value ==6){
            finalTitle="Six";
            this.letter = "6";
        }else if(value ==7){
            finalTitle="Seven";
            this.letter = "7";
        }else if(value ==8){
            finalTitle="Eight";
            this.letter = "8";
        }else if(value ==9){
            finalTitle="Nine";
            this.letter = "9";
        }else if(value ==10){
            finalTitle="Ten";
            this.letter = "10";
        }else if(value ==11){
            finalTitle="Jack";
            this.letter = "J";
        }else if(value ==12){
            finalTitle="Queen";
            this.letter = "Q";
        }else if(value ==13){
            finalTitle="King";
            this.letter = "K";
        }else{
            finalTitle = "False Value";
        }
        return finalTitle;
    }

    @Override
    public String toString() {
        return title;
    }
}
