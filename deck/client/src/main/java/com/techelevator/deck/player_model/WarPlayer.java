package com.techelevator.deck.player_model;

import com.techelevator.deck.model.Card;
import com.techelevator.deck.model.Hand;

public class WarPlayer extends Player {
    public WarPlayer(Hand hand) {
        super(hand);
    }
    public Card playTurn(){
        return this.hand.playTopCard();
    }

}
