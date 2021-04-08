Peter Gilbert
March 2021
BlackJack Project

Included Classes: 
Blackjack.java = the main class for Blackjack that uses all of the other objects in conjunction to create the game
BlackjackHand.java = an extension of the Hand class that contains everything Hand has in addition to storing the amount of money bet on a hand.
BlackjackPlayer.java = an extension of a CardPlayer but includes a wallet that stores how much money a player currently has
Card.java = A simple card class meant to be extended for future projects that contains a boolean that correlates with whether it is faceup or down
CardPlayer.java = CardPlayer is an extension of Player that is meant for card games and allows a player to have multiple hands of cards
Dealer.java = Dealer is an extension of CardPlayer that is currently the same as cardPlayer had all the functionality, but could be extended for future games
Deck.java = Deck is a simple class meant to create a deck object which is an ArrayList of cards. it's meant to be extended by any card game
Hand.java = Hand is a simple class similar to deck that is an arraylist of cards meant for a player to have as their hand
Launcher.java = Launcher is a simple class that does nothing but launch the game
Player.java = Player is a simple class meant to be extended that contains the player's name 
stdCard.java = stdCard extends Card and contains the rank and Suit where suit is an enum and rank corresponds to a standard deck of cards
stdDeck.java = stdDeck extends Deck and creates a deck of 52 stdCards aka a standard deck of cards
Suit.java = Suit is an enum class that corresponds to each suit in a standard deck of cards

To compile the program: please compile everything in the folder using javac, the name of the folder, and *.java

To run the program:
run the launcher with: "java Launcher" and follow the on screen instructions

Some info:
Splits are limited to only being able to split once. I read the post on piazza about being able to limit it ourselves so I chose this.

I wrote most of this program with the idea that it should be able to handle more players, so it should work for any number of players, although i have yet to thoroughly test it as it is not needed for this assignment.

