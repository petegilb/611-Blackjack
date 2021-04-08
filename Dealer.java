//Code by Peter Gilbert
//CS611 TicTacToe February 2021
//dealer class for card games specifically
public class Dealer extends CardPlayer{
    public Dealer(){
        super();
    }
    //make a dealer with a specific name
    public Dealer(String name){
        super(name);
    }
    //make a dealer with a specific name and numHands
    public Dealer(String name, int numHands){
        super(name, numHands);
    }
}
