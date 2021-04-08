//Code by Peter Gilbert
//CS611 TicTacToe February 2021
//Player class that stores what pieces a player can use and can be extended for teams

public class Player{
    protected String name;

    //default constructor
    public Player(){
        name = "default";
    }
    public Player(String name){
        this.name = name;
    }

    //accessors
    public String getName(){
        return this.name;
    }

    //mutators
    public void setName(String str){
        name = str;
    }

}
