//Code by Peter Gilbert
//CS611 Blackjack March 2021
//extends Deck and is a deck of 52 standard cards
//aka a standard deck of cards
public class stdDeck extends Deck{
    //default constructor
    //generates a deck of 52 cards
    public stdDeck(){
        super();
        for(int i=1; i<14; i++){
            stdCard card = new stdCard(Suit.CLUBS, i);
            deck.add(card);
            card = new stdCard(Suit.DIAMONDS, i);
            deck.add(card);
            card = new stdCard(Suit.HEARTS, i);
            deck.add(card);
            card = new stdCard(Suit.SPADES, i);
            deck.add(card);
        }
    }
}
