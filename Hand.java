//Code by Peter Gilbert
//CS611 Blackjack March 2021
//Hand class that is an arraylist of cards
//each card player will have a hand of cards
//similar to deck
import java.util.ArrayList;
import java.util.Collections;
public class Hand{
    protected ArrayList<Card> hand;
    //default constructor
    public Hand(){
        hand = new ArrayList<>();
    }
    //add a card to the hand
    public void addCard(Card card){
        hand.add(card);
    }
    //add a card to the hand at an index
    public void addCardIndex(Card card, int index){
        hand.add(index, card);
    }
    //remove a card from the hand
    public void removeCard(Card card){
        hand.remove(card);
    }
    //remove a card at an index
    public void removeCardIndex(int index){
        hand.remove(index);
    }
    //get a card at an index
    public Card getCardIndex(int index){
        return hand.get(index);
    }

    //shuffle a hand 
    public void shuffleHand(){
        Collections.shuffle(hand);
    }

    //prints the Hand
    public void printHand(){
        System.out.println(hand);
    }

    //returns number of cards in the hand
    public int getNumCards(){
        return hand.size();
    }

    //accessor
    public ArrayList<Card> getHand(){
        return hand;
    }

    //mutator
    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }
}
