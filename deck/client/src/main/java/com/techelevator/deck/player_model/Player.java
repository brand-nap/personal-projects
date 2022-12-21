package com.techelevator.deck.player_model;

import com.techelevator.deck.model.Hand;

public class Player {
    Hand hand;
    int points = 0;
    public Player(Hand hand) {
        this.hand = hand;
    }
    public Player() {}
    public int addPoints(int pts){
        this.points+=pts;
        return this.points;
    }
    public int getPoints(){
        return this.points;
    }


}
