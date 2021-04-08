//Code by Peter Gilbert
//CS611 TicTacToe February 2021
//player class for card games specifically
import java.util.ArrayList;
import java.util.Collections;
public class CardPlayer extends Player{
    //variables
    //protected Hand hand;
    protected Hand[] hands;

    public CardPlayer(){
        super();
        hands = new Hand[2];
        //create hands for each index in hands
        for(int i=0;i<hands.length; i++){
            hands[i] = new Hand();
        }
    }
    //set the player's name as well
    public CardPlayer(String name){
        super(name);
        hands = new Hand[2];
        for(int i=0;i<hands.length; i++){
            hands[i] = new Hand();
        }
    }
    //set the player's name as well
    public CardPlayer(String name, int numHands){
        super(name);
        hands = new Hand[numHands];
        for(int i=0;i<hands.length; i++){
            hands[i] = new Hand();
        }
    }

    //prints the Hand
    public void printHand(int index){
        hands[index].printHand();
    }
    //print out the first hand
    public void printFirstHand(){
        hands[0].printHand();
    }
    //print all hands
    public void printAllHands(){
        for(int i=0;i<hands.length; i++){
            System.out.println("Player " + name + " Hand " + i + ":");
            hands[i].printHand();
        }
    }
    
    //reset the value of all hands
    public void resetHands(){
        for(int i=0;i<hands.length; i++){
            hands[i] = new Hand();
        }
    }

    //accessor
    public Hand[] getHands(){
        return hands;
    }

    public Hand getHand(int index){
        return hands[index];
    }

    public Hand getFirstHand(){
        return hands[0];
    }

    public int getNumHands(){
        return hands.length;
    }

    //mutator
    public void setHand(Hand hand, int index){
        this.hands[index] = hand;
    }

    public void setHands(Hand[] hands){
        this.hands = hands;
    }
}
