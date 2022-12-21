package com.techelevator.deck.player_model;
import com.techelevator.deck.model.Card;
import com.techelevator.deck.model.Hand;
import com.techelevator.deck.player_model.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FfPlayer extends Player {
    boolean hasHighCard = false;
    List<Card> cardsPlayed = new ArrayList<>();
    int[] preferredSuit = new int[]{0, 0, 0, 0};
    int bidToReach = 0;
    int roundPoints = 0;

    List<Integer> redInSuit = new ArrayList<>(Arrays.asList(2, 3, 4, 6, 7, 8, 9, 10, 12, 13, 1, 11, 5));
    List<Integer> blackInSuit = new ArrayList<>(Arrays.asList(10, 9, 8 ,7, 6, 4, 3, 2, 12, 13, 1, 11, 5));
    List<Integer> blackRanking = new ArrayList<>(Arrays.asList(10, 9, 8 ,7, 6, 5,4, 3, 2, 1, 11, 12, 13));
    List<Integer> redRanking = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11, 12, 13));

    String inSuit;
    public FfPlayer() {
        preferredSuit = new int[]{0, 0, 0, 0};
    }



    public void pickUp(Hand hand){
        for(int i = 0; i < hand.size(); i++) {
            this.hand.pullCard(hand.playTopCard());
        }
        this.hand = orderHand(this.hand);
    }


    public String bid(int previousBid){
        int bestSuit = 0;
        int suitIndex = -1;
        for(int i = 0; i<4; i++){
            if(preferredSuit[i]>bestSuit) {
                bestSuit = preferredSuit[i];
                suitIndex = i;
            }
        }
        if((bestSuit>14 && previousBid==0)||(bestSuit>26 && previousBid==15)){
            return (suitIndex==0)?"Clubs":(suitIndex==1)?"Diamonds":(suitIndex==2)?"Spades":"Hearts";
        }
        return "Pass";
    }
    public void setBidToReach(int bid){
        this.bidToReach = bid;
    }
    public int getBidToReach(){
        return bidToReach;
    }
    public boolean madeBid(){
        if(roundPoints>=bidToReach){
            this.points+= roundPoints;
            bidToReach = 0;
            roundPoints=0;
            return true;
        }
        this.points-= bidToReach;
        bidToReach = 0;
        roundPoints=0;
        return false;
    }


    private Hand orderHand(Hand hand){
        Hand result = new Hand();
        preferredSuit = new int[]{0,0,0,0};
        String tempSuit = "";
        for(int i = 0; i<4; i++){
            tempSuit = (i==0)?"Clubs":(i==1)?"Diamonds":(i==2)?"Spades":"Hearts";
            int size = hand.size();
            for(int j = 0; j < size; j++){
                Card cardPulled = hand.playTopCard();
                if(cardPulled.getSuit()==tempSuit){
                    preferredSuit[i] += (i==0||i==3)? blackInSuit.indexOf(cardPulled.getValue()) : redInSuit.indexOf(cardPulled.getValue());
                    result.pullCard(cardPulled);
                }else{
                    hand.pullCard(cardPulled);
                }
            }
        }
        return result;
    }


    //MODEL FOR FORTY FIVES PLAYER
    //handed 5 cards
    //orders cards
    //deciphers whether to bid and in which suit
    //if they win the bid                                            if they dont
    //pick up kitty                             discard all off suit except Kings
    //discard all off suit except Kings                request x cards to reach 5
    //request x cards to reach 5

    //play rounds as bidder vs. as setter
}
