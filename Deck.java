//Code by Peter Gilbert
//CS611 Blackjack March 2021
//Deck class that is an arraylist of cards

import java.util.ArrayList;
import java.util.Collections;
public class Deck{
    //variables
    protected ArrayList<Card> deck;
    //a deck has an arraylist of cards
    public Deck(){
        deck = new ArrayList<>();
    }

    //add a card to the deck
    public void addCard(Card card){
        deck.add(card);
    }
    //add a card to the deck at an index
    public void addCardIndex(Card card, int index){
        deck.add(index, card);
    }
    //remove a card from the deck
    public void removeCard(Card card){
        deck.remove(card);
    }
    //remove a card at an index
    public void removeCardIndex(int index){
        deck.remove(index);
    }
    //get a card at an index
    public Card getCardIndex(int index){
        return deck.get(index);
    }

    //shuffle a deck
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    //prints the deck
    public void printDeck(){
        System.out.println(deck);
    }

    //accessor
    public ArrayList<Card> getDeck(){
        return deck;
    }

    //mutator
    public void setDeck(ArrayList<Card> deck){
        this.deck = deck;
    }
}
