//Code by Peter Gilbert
//CS611 Blackjack March 2021
//main class for Blackjack that uses the other objects and is launched
//through the launcher by calling the playGame() function
import java.util.Scanner;
import java.util.ArrayList;
public class Blackjack{
    //variables
    private Scanner getInput;
    private boolean isOver = false; //is the game over or not
    private BlackjackPlayer[] players;
    private Dealer dealer;
    private final int defaultPlayers = 1; //final int for how many players there are by default
    private int[] currentBets; //array containing bets for each player
    private stdDeck deck;
    private final int blackjackVal = 21; //value for blackjack
    private final int dealerMax = 17; //value that dealer needs to draw to

    //default constructor
    public Blackjack(){
    }
    //playGame function that starts the game
    //players can only split once
    public void playGame(){
        //initialization
        deck = new stdDeck();
        isOver = false;
        getInput = new Scanner(System.in);
        //could ask for additional players here:
        checkNumPlayers(); //currently set to two for this project
        players = new BlackjackPlayer[defaultPlayers];
        for (int i=0; i<defaultPlayers; i++){
            players[i] = new BlackjackPlayer();
        }
        //create dealer
        dealer = new Dealer();
        //init currentBets
        currentBets = new int[players.length];
        for (int i=0; i<players.length; i++){
            currentBets[i] =0;
        }

        while(isOver == false){
            //start a new round
            newRound();
            //check if the player wants to cash out or keep going
            if(checkQuit()){
                break;
            }
        }

    }


    public void newRound(){
        //generate and shuffle a new deck
        deck = new stdDeck();
        deck.shuffleDeck();
        //allow each player to bet their money
        for (int i=0; i<players.length; i++){
            players[i].resetHands();
            players[i].printWallet();
            betMoney(i);
        }
        dealer.resetHands();
        //give out the initial cards from the deck
        giveInitialCards();
        playerTurn();
        if(checkOver()){
            if(checkBankrupt()){
                System.out.println("Player has run out of money! Game over!");
                isOver = true;
            }
            return;
        }
        else{
            dealerTurn();
        }
        if(checkOver()){
            if(checkBankrupt()){
                System.out.println("Player has run out of money! Game over!");
                isOver = true;
            }
            return;
        }
        else{
            compareHands(); //compare player hands and dealer hands
        }
        //check if players are bankrupt
        if(checkBankrupt()){
            System.out.println("Player has run out of money! Game over!");
            isOver = true;
            return;
        }


    }

    //allow the players to do to their turns
    public void playerTurn(){
        for (int i=0; i<players.length; i++){ //for each player
            for(int x=0; x<players[i].getNumHands(); x++){ //for each hand
                BlackjackHand temp = players[i].getHand(x);
                //if the hand is valid
                if(temp.getNumCards() > 0){
                    System.out.println("");
                    askPlayerTurn(i, temp);

                }
            }
        }
    }

    public void askPlayerTurn(int playerIndex, BlackjackHand hand){
        cleanPrintHand(playerIndex, hand);
        //if the hand is busted, we're done with this one
        if(isBust(hand)){
            System.out.println("This hand busted!");
            return;
        }
        System.out.println("Would you like to Hit, Stand, Split, or Double Up?");
        System.out.println("Type 1, 2, 3, or 4 respectively to choose...");
        int choice = 0;
        try{
            choice = Integer.parseInt(getInput.nextLine());
        }
        catch(Exception e){
            System.out.println("Bad input. Try again.");
            askPlayerTurn(playerIndex, hand);
        }
        switch(choice){
            case 1://hit case
                handHit(playerIndex, hand);
                break;
            case 2://stand case
                //do nothing which counts as a stand
                break;
            case 3://split case
                //since we can only have max 2 hands we check if there is already one hand
                if(players[playerIndex].getNumHands() == 1 && checkSplit(playerIndex, hand)){
                    //split here
                    handSplit(playerIndex, hand);
                }
                else{
                    System.out.println("Can't split, try another option.");
                    askPlayerTurn(playerIndex, hand);
                }
                break;
            case 4://double up case
                if(canDoubleUp(playerIndex, hand)){
                    doubleUp(playerIndex, hand);
                }
                else{
                    System.out.println("Can't doubleUp, try another option.");
                    askPlayerTurn(playerIndex, hand);
                }
                break;
            default: //bad input
                System.out.println("Bad input. Try again.");
                askPlayerTurn(playerIndex, hand);
                break;
        }
    }

    //dealer draws until a set value dealerMax (17 in normal blackjack)
    //then compare the hands between the player and the dealer
    //also flips over the second card
    public void dealerTurn(){
        System.out.println("");
        System.out.println("Dealer Turn..");
        Hand dealerHand = dealer.getFirstHand();
        //first flip over the second card
        dealerHand.getCardIndex(1).setFaceUp(true);
        //now let's draw cards until dealerMax
        while(getHandValue(dealerHand) < dealerMax){
            System.out.println("Dealer drew a card.");
            dealerHand.addCard(getTopCard());
        }
        cleanPrintDealer();

    }

    //splits a player's hand into 2
    //assuming a hand has 2 cards and 2 cards of the same rank
    //and that a player currently has only 1 hand
    public void handSplit(int playerIndex, BlackjackHand hand){

        BlackjackHand hand2 = players[playerIndex].getHand(1);
        hand2.addCard(hand.getCardIndex(0)); //add the card into the other hand
        //remove the second card from the first hand
        hand.removeCardIndex(1);
        //add cards to each hand from the top of the deck
        hand.addCard(getTopCard());
        hand2.addCard(getTopCard());
        //place the bet from the first hand
        hand2.setBet(hand.getBet());
        players[playerIndex].reduceMoney(hand2.getBet());

        //call the turn for the first hand again
        askPlayerTurn(playerIndex, hand);
    }

    //player hits and gains a card
    public void handHit(int playerIndex, BlackjackHand hand){
        //add the top card of the deck to the hand
        hand.addCard(getTopCard());
        askPlayerTurn(playerIndex,hand);
    }

    //method to see if the player can double up
    //if they have enough money
    public boolean canDoubleUp(int playerIndex, BlackjackHand hand){
        int currWallet = players[playerIndex].getWallet();
        if(currWallet - hand.getBet() <= 0){
            return false;
        }
        return true;
    }

    //the method that doubles up
    public void doubleUp(int playerIndex, BlackjackHand hand){
        int currWallet = players[playerIndex].getWallet();
        players[playerIndex].setWallet(currWallet - hand.getBet());
        hand.doubleBet(); //double the bet
        hand.addCard(getTopCard()); //hit
        //do nothing which counts as a stand
        cleanPrintHand(playerIndex, hand);
    }

    //check if we can split a hand
    public boolean checkSplit(int playerIndex, BlackjackHand hand){
        int currWallet = players[playerIndex].getWallet();
        //if it has 2 cards and enough money
        if(hand.getNumCards() == 2 && (currWallet - hand.getBet() > 0)){
            stdCard temp1 = (stdCard) hand.getCardIndex(0);
            stdCard temp2 = (stdCard) hand.getCardIndex(1);
            //and the ranks are equal
            if(temp1.getRank() == temp2.getRank())
                return true;
        }
        return false;
    }

    //check if the game is over
    public boolean checkOver(){
        //check if all of a player's hands are bust
        int numPlayersBusted = 0;
        int numNatBlackjacks = 0;
        for (int i=0; i<players.length; i++){
            //first check if the player got a natural blackjack
            if(isNaturalBlackjack(players[i])){
                numNatBlackjacks++;
                playerWin(i); //the player wins with a nat blackjack and is done
            }
            else{

                int numBusted = 0;
                Hand[] temp = players[i].getHands();
                int numHands = players[i].getNumHands();
                //check every hand
                for(int x=0; x<numHands; x++){
                    if(isBust(temp[x])){
                        System.out.println("");
                        System.out.println("The current hand has busted!");
                        //if a hand is a bust we clear their bet
                        players[i].getHand(x).clearBet();
                        players[i].setHand(new BlackjackHand(), x); //reset the hand
                        numBusted++;
                    }
                }
                if(numBusted == numHands){
                    numPlayersBusted++;
                    System.out.println("");
                    System.out.println("Player " + players[i].getName() + " has busted!");
                    //reset player's hands
                    players[i].resetHands();
                }
            }

        }
        if(numNatBlackjacks == players.length){
            System.out.println("All players have had Natural Blackjacks!");
            return true;
        }
        //check if all players have busted
        if(numPlayersBusted == players.length){
            System.out.println("All players have busted! Dealer wins!");
            return true;
        }

        //check if the dealer has busted
        if(isBust(dealer.getFirstHand())){
            System.out.println("The dealer has busted! Players win!!");
            //give players money here
            //every player that hasn't busted should get their money
            for (int i=0; i<players.length; i++){
                playerWin(i);
            }
            return true;
        }

        return false;
    }

    //called when the dealer loses and gives players money depending on their bets
    //int player is the index in the players array
    public void playerWin(int player){
        if(players[player].getAllBets()>0){
            //add money based off the current bet
            System.out.println("Player: " + players[player].getName() + " wins $" + players[player].getAllBets()*2);
            players[player].addMoney(players[player].getAllBets()*2);
            //reset their hands now that they've got their payout
            players[player].resetHands();
        }

    }

    //compares the players and the dealers hands
    //reports the wins if they occur
    public void compareHands(){
        boolean isTie = false;
        for(int i=0; i<players.length; i++){ //for each player
            int numHands = players[i].getNumHands();
            for(int x=0; x<numHands; x++){ //for each hand
                BlackjackHand playerHand = players[i].getHand(x);
                Hand dealerHand = dealer.getFirstHand();
                if(getHandValue(playerHand) > getHandValue(dealerHand)){
                    handWin(i, x); //then this hand is a winner
                }
                else if(getHandValue(playerHand) == getHandValue(dealerHand)){
                    handTie(i, x); //then this hand is a tie
                    isTie = true;
                }
                else{
                    handLoss(i, x);
                }
            }
        }
        if(isTie){
            System.out.println("A new round will now begin...");
            System.out.println("");
            newRound();
        }

    }

    //returns true if the game needs to end because there is no players left
    //else checks if a player is bankrupt and if they are calls bankrupt which removes them
    public boolean checkBankrupt(){
        for(int i=0;i<players.length;i++){
            if(players[i].getWallet() <= 0){
                System.out.println("Player " + players[i].getName() + " has gone bankrupt!");
                if(players.length == 1){ //if a player is bankrupt and we only have 1 player, true
                    return true;
                }
                bankrupt(i);
            }
        }
        return false;
    }

    //player runs out of money then we delete the player from the array
    public void bankrupt(int playerIndex){
        if(players.length > 1){
            BlackjackPlayer[] newPlayers = new BlackjackPlayer[players.length-1];
            int newPlayersIndex = 0;
            for(int i=0;i<players.length;i++){
                //if it is not the one indicated we copy it
                if(!(i==playerIndex)){
                    newPlayers[newPlayersIndex]=players[i];
                    newPlayersIndex++;
                }
            }
        }

    }


    //handindex represents which hand within a players hand
    //playerindex represents the index of the player in players
    public void handLoss(int playerIndex, int handIndex){
        int losses = players[playerIndex].getHand(handIndex).getBet();
        players[playerIndex].getHand(handIndex).clearBet(); //clear the bet
        players[playerIndex].setHand(new BlackjackHand(), handIndex); //reset the hand
        System.out.println("Player: " + players[playerIndex].getName() + " loses $" + losses + " to the dealer");
    }

    //give money back for a tie
    //handindex represents which hand within a players hand
    //playerindex represents the index of the player in players
    public void handTie(int playerIndex, int handIndex){
        int betMoney = players[playerIndex].getHand(handIndex).getBet();
        players[playerIndex].addMoney(betMoney);
        players[playerIndex].getHand(handIndex).clearBet(); //clear the bet
        players[playerIndex].setHand(new BlackjackHand(), handIndex); //reset the hand
        System.out.println("Player: " + players[playerIndex].getName() + " ties with the dealer");
    }

    //give money for a single hand winning
    //handindex represents which hand within a players hand
    //playerindex represents the index of the player in players
    public void handWin(int playerIndex, int handIndex){
        //add double the money to the player's wallet
        int winnings = players[playerIndex].getHand(handIndex).getBet()*2;
        players[playerIndex].addMoney(winnings);
        players[playerIndex].getHand(handIndex).clearBet(); //clear the bet
        players[playerIndex].setHand(new BlackjackHand(), handIndex); //reset the hand
        System.out.println("Player: " + players[playerIndex].getName() + " wins $" + winnings);

    }

    //check if a blackjack player's hand is bust
    public boolean isBust(Hand hand){
        if(getHandValue(hand) > blackjackVal){
            return true;
        }
        return false;
    }

    //gives out the initial cards in a round
    //this method doesn't have to deal with multiple decks since the player will not have
    //been able to split yet
    public void giveInitialCards(){
        //give cards to each player
        for(int i=0; i<players.length;i++){
            for(int x=0; x<2;x++){
                players[i].getFirstHand().addCard(getTopCard());
            }
            System.out.println("");
            players[i].printFirstHand();
            System.out.println("Player " + players[i].getName() + " has value: " + getHandValue(players[i].getFirstHand()));
        }

        //give cards to dealer
        dealer.getFirstHand().addCard(getTopCard());
        stdCard temp = getTopCard();
        //make sure one of the cards is face down
        temp.setFaceUp(false);
        dealer.getFirstHand().addCard(temp);
        System.out.println("");
        dealer.printFirstHand();;
    }

    //get the value of a player's hand
    //makes all face cards give a val of 10
    //defaults ace to 11 unless it busts
    public int getHandValue(Hand hand){
        int value=0;
        int numAces = 0;
        for(int i=0; i<hand.getNumCards(); i++){
            stdCard temp = (stdCard) hand.getCardIndex(i);
            if(temp.getRank() == 1){
                numAces++;
                value+=11;
            }
            //if it's a jack queen or king add 10
            else if(temp.getRank() == 11 || temp.getRank() == 12 || temp.getRank() == 13){
                value+=10;
            }
            else //otherwise add the rank
                value += temp.getRank();
        }
        //if the val is over 21 and there are some aces
        //then we make the ace value to 1 by deleting 10
        while(value > blackjackVal && numAces > 0){
            value-= 10;
            numAces--;
        }
        return value;
    }

    //check if the player has a natural blackjack
    public boolean isNaturalBlackjack(BlackjackPlayer player){
        if(player.getFirstHand().getNumCards() == 2 && player.getNumHands() == 1){
            stdCard temp = (stdCard) player.getFirstHand().getCardIndex(0);
            int card1Rank = temp.getRank(); //rank of the first card
            temp = (stdCard) player.getFirstHand().getCardIndex(1);
            int card2Rank = temp.getRank(); //rank of the second card
            //if a card is an ace and the other is 10,j,q,k
            if(card1Rank == 1 && card2Rank >=10){
                System.out.println("Natural Blackjack!");
                return true;
            }
            else if(card2Rank == 1 && card1Rank>=10){
                System.out.println("Natural Blackjack!");
                return true;
            }
        }
        return false;
    }

    //get and remove the top card off the deck
    public stdCard getTopCard(){
        stdCard temp = (stdCard) deck.getCardIndex(0);
        deck.removeCardIndex(0);
        return temp;
    }

    //allows a player to bet their money if they have enough
    //int player is the index of the player in players
    public void betMoney(int player){
        System.out.println("How much would you like to bet? Please enter just the integer.");
        try{
            int temp = Integer.parseInt(getInput.nextLine());
            //if the player has enough money, set the bet
            if(temp <= players[player].getWallet() && temp>0){
                players[player].getFirstHand().setBet(temp);
                players[player].reduceMoney(temp); //get rid of the money in their wallet
            }
            else{
                System.out.println("You don't have that much money!");
                betMoney(player);
            }
        }
        catch(Exception e){
            System.out.println("Incorrect input, try again");
            betMoney(player);
        }
    }

    public boolean checkQuit(){
        if(isOver == true)
            return true;
        System.out.println("");
        System.out.println("=======================");
        System.out.println("Do you want to keep playing? (Y/N)");
        String str = getInput.nextLine();
        if(! (str.equals("Y") || str.equals("y"))){
            isOver = true;
            return true;
        }
        return false;
    }

    //prints a clean message and a hand
    public void cleanPrintHand(int playerIndex, BlackjackHand hand){
        System.out.println("");
        System.out.println("Player " + players[playerIndex].getName() + ", your current hand is: ");
        hand.printHand();
        System.out.println("With a value of: " + getHandValue(hand));
        System.out.println("");
    }

    //prints dealer info
    public void cleanPrintDealer(){
        System.out.println("");
        System.out.println("Dealer, your current hand is: ");
        dealer.getFirstHand().printHand();
        System.out.println("With a value of: " + getHandValue(dealer.getFirstHand()));
        System.out.println("");
    }

    //asks user for number of players at the beginning
    public int checkNumPlayers(){
        int numPlayers = 0;
        try{
            System.out.println("Please type number of Players (as an int up to 2)");
            numPlayers = Integer.parseInt(getInput.nextLine());
        }
        catch(Exception e){
            System.out.println("Invalid Input. Try again");
            checkNumPlayers();
        }
        return numPlayers;
    }

    //check if a card is an ace
    public boolean isAce(stdCard card){
        if(card.getRank() == 1)
            return true;
        else
            return false;
    }

}
