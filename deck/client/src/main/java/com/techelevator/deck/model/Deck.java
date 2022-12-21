package com.techelevator.deck.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> orderedDeck = new ArrayList<>();
    private List<Card> shuffledDeck = new ArrayList<>();

    public Deck(){
        for (int i = 0; i < 52; i++) {
            this.orderedDeck.add(new Card(i+1));
        }
        List<Card> preshuffled = shuffle(orderedDeck);
        for (int i = 0; i < (int) Math.random()*5 + 2; i++) {
            preshuffled = shuffle(preshuffled);
        }
        this.shuffledDeck=preshuffled;
    }

    public Deck(int shuffles){
        shuffles = shuffles * (int) (Math.random()*5) + 1;
        if(shuffles < 0){
            shuffles*=-1;
        }
        for (int i = 0; i < 52; i++) {
            this.orderedDeck.add(new Card(i+1));
        }
        List<Card> preshuffled = shuffle(orderedDeck);
        for (int i = 0; i < shuffles; i++) {
            preshuffled = shuffle(preshuffled);
        }
        this.shuffledDeck=preshuffled;
    }

    private List<Card> shuffle(List<Card> unshuffled){
        List<Integer> done = new ArrayList<>();
        List<Card> preshuffled = new ArrayList<>();
        while (done.size()!=unshuffled.size()){
            int firstRandInt = (int) (Math.random()*13) + 1;
            int secRandInt = (int) (Math.random()*4);
            int thirdRandInt = (int) (Math.random()*unshuffled.size()) + 1;

            if(firstRandInt+(13*secRandInt)==thirdRandInt && !done.contains(thirdRandInt)){
                preshuffled.add(unshuffled.get(thirdRandInt-1));
                done.add(thirdRandInt);
            }
        }
        return preshuffled;
    }

    public void shuffleExisting(int shuffles){
        shuffles = shuffles * (int) (Math.random()*5) + 1;
        if(shuffles < 0){
            shuffles*=-1;
        }
        List<Card> preshuffled = shuffle(orderedDeck);
        for (int i = 0; i < shuffles; i++) {
            preshuffled = shuffle(preshuffled);
        }
        this.shuffledDeck = preshuffled;
    }

    public Card pullCard(){
        try {
            Card topCard = this.shuffledDeck.remove(0);
            this.orderedDeck.remove(topCard);
            return topCard;
        }catch(Exception e){
            return null;
        }
    }


    public String toString(boolean shuffled) {
        String returnedDeck = "\n\n";
        if (shuffled){
            String str = "";
            List<Card> tempDeck = new ArrayList<>();
            for(Card card : shuffledDeck){
                tempDeck.add(card);
            }
            int cardCount = 0;
            while(tempDeck.size()>0) {
                cardCount++;
                returnedDeck += "" +cardCount + ". " + tempDeck.remove(0) + " \n";
            }
        }else {
            returnedDeck += "                              ";
            List<List<Card>> suits = new ArrayList<>();
            suits.add(singleSuit(orderedDeck, "Clubs"));
            suits.add(singleSuit(orderedDeck, "Diamonds"));
            suits.add(singleSuit(orderedDeck, "Spades"));
            suits.add(singleSuit(orderedDeck, "Hearts"));
            for (int i = 1; i < 14; i++) {
                for (List<Card> singleSuit : suits) {
                    String str = "";
                    if(singleSuit.size() > 0) {
                        if (singleSuit.get(0).getValue() == i) {
                            str += singleSuit.remove(0);
                        }
                    }
                    while(str.length()<47){
                        str+=" ";
                    }
                    returnedDeck+= str;
                }
                returnedDeck += "\n                              ";
            }
        }
        return returnedDeck;
    }

    private List<Card> singleSuit(List<Card> deck, String suit){
        List<Card> tempDeck = new ArrayList<>();
        for (Card card : deck) {
            if(card.getSuit().equals(suit)){
                tempDeck.add(card);
            }
        }

        return tempDeck;
    }
}
