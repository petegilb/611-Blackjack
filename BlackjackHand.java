//Code by Peter Gilbert
//CS611 Blackjack March 2021
//blackjack hand class that also has a value of the bet on the hand
public class BlackjackHand extends Hand{
    private int betMoney;
    public BlackjackHand(){
        super();
        betMoney = 0;
    }

    //accessors
    public int getBet(){
        return betMoney;
    }

    //modifiers
    public void setBet(int newBet){
        betMoney = newBet;
    }

    //double the bet
    public void doubleBet(){
        betMoney*=2;
    }

    //clear the bet
    public void clearBet(){
        betMoney = 0;
    }
}
