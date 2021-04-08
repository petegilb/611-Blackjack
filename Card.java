//Code by Peter Gilbert
//CS611 Blackjack March 2021
//class for a basic card that can be extended for more specific use
//stores a value for whether the card is face up for face down
public class Card{
    //face up or face down attribute
    private boolean isFaceUp;

    //default constructor
    public Card(){
        //card is up by default
        isFaceUp = true;
    }
    //constructor to make a card that is either up or down
    public Card(boolean isUp){
        isFaceUp = isUp;
    }
    //accessor
    public boolean getFaceUp(){
        return isFaceUp;
    }

    //mutator
    //true means it is face up
    public void setFaceUp(boolean isUp){
        this.isFaceUp = isUp;
    }
}
