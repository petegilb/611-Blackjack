//Code by Peter Gilbert
//CS611 Blackjack March 2021
//standard card class that has a suit and rank
//like a card from a standard deck of cards
public class stdCard extends Card{
    //variables
    //suit corresponding to a suit
    private Suit suit;
    //rank from 1-13 corresponding to ace, 2...10, jack, queen, king
    private int rank;

    //default constructor
    public stdCard(){
        super();
        suit = Suit.SPADES;
        rank = 2;
    }
    //constructor to set suit and rank
    public stdCard(Suit suitSet, int rankSet){
        super();
        suit = suitSet;
        rank = rankSet;
    }
    //constructor to set suit and rank and if it's face up or down
    public stdCard(Suit suitSet, int rankSet, boolean isFaceUp){
        super(isFaceUp);
        suit = suitSet;
        rank = rankSet;
    }

    //accessor
    public Suit getSuit(){
        return suit;
    }
    public int getRank(){
        return rank;
    }

    //mutator
    public void setSuit(Suit setter){
        suit = setter;
    }
    public void setRank(int val){
        rank = val;
    }

    @Override
    public String toString(){
        String toPrint = "(";
        if(!(getFaceUp())){
            return "(X)";
        }
        switch(rank){
            case 1:
                toPrint+= "A | ";
                break;
            case 2:
                toPrint+= "2 | ";
                break;
            case 3:
                toPrint+= "3 | ";
                break;
            case 4:
                toPrint+= "4 | ";
                break;
            case 5:
                toPrint+= "5 | ";
                break;
            case 6:
                toPrint+= "6 | ";
                break;
            case 7:
                toPrint+= "7 | ";
                break;
            case 8:
                toPrint+= "8 | ";
                break;
            case 9:
                toPrint+= "9 | ";
                break;
            case 10:
                toPrint+= "10 | ";
                break;
            case 11:
                toPrint+= "J | ";
                break;
            case 12:
                toPrint+= "Q | ";
                break;
            case 13:
                toPrint+= "K | ";
                break;
        }
        switch(suit){
            case CLUBS:
                toPrint+= "CLUBS";
                break;
            case DIAMONDS:
                toPrint+= "DIAMONDS";
                break;
            case HEARTS:
                toPrint+= "HEARTS";
                break;
            case SPADES:
                toPrint+= "SPADES";
                break;

        }
        toPrint+=")";
        return toPrint;
    }
}
