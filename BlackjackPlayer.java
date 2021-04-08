//Code by Peter Gilbert
//CS611 TicTacToe February 2021
//player class for blackjack specifically
//which is a card game so it extends cardplayer

public class BlackjackPlayer extends CardPlayer{
    private int wallet; //amount of money the player has
    private final int defaultMoney = 5000;
    public BlackjackPlayer(){
        super();
        //reset hands to a blackjack hand instead of a normal hand
        hands = new BlackjackHand[2];
        //create hands for each index in hands
        for(int i=0;i<hands.length; i++){
            hands[i] = new BlackjackHand();
        }
        //set the wallet to the default value
        wallet = defaultMoney;
    }
    //make a blackjack player with a name
    public BlackjackPlayer(String name){
        super(name);
        //reset hands to a blackjack hand instead of a normal hand
        hands = new BlackjackHand[2];
        //create hands for each index in hands
        for(int i=0;i<hands.length; i++){
            hands[i] = new BlackjackHand();
        }

        wallet = defaultMoney;
    }
    //make a blackjack player with a set wallet
    public BlackjackPlayer(int money){
        super();
        //reset hands to a blackjack hand instead of a normal hand
        hands = new BlackjackHand[2];
        //create hands for each index in hands
        for(int i=0;i<hands.length; i++){
            hands[i] = new BlackjackHand();
        }
        wallet = money;
    }
    //make a blackjack player with a set wallet and name
    public BlackjackPlayer(int money, String name){
        super(name);
        //reset hands to a blackjack hand instead of a normal hand
        hands = new BlackjackHand[2];
        //create hands for each index in hands
        for(int i=0;i<hands.length; i++){
            hands[i] = new BlackjackHand();
        }
        wallet = money;
    }

    //override the resetHands function
    //reset the value of all hands
    public void resetHands(){
        for(int i=0;i<hands.length; i++){
            hands[i] = new BlackjackHand();
        }
    }

    //print current money
    public void printWallet(){
        System.out.println("Player " + name + " has: $" + wallet);
    }

    //add money to the wallet
    public void addMoney(int toAdd){
        wallet += toAdd;
    }

    //reduce money
    public void reduceMoney(int toReduce){
        wallet-= toReduce;
    }

    //accessors
    //override these functions from card player to return the right type
    public int getWallet(){
        return wallet;
    }

    public BlackjackHand[] getHands(){
        return (BlackjackHand[]) hands;
    }

    public BlackjackHand getHand(int index){
        return (BlackjackHand) hands[index];
    }

    public BlackjackHand getFirstHand(){
        return (BlackjackHand) hands[0];
    }

    public int getNumHands(){
        int count = 0;
        for(int i=0; i<hands.length; i++){
            if(hands[i].getNumCards() > 0)
                count++;
        }
        return count;
    }
    //return the total of all bets
    public int getAllBets(){
        int total = 0;
        for(int i=0;i<hands.length; i++){
            total += getHand(i).getBet();
        }
        return total;
    }
    //mutators
    public void setWallet(int newWallet){
        wallet = newWallet;
    }

}
