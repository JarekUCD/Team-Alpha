import java.util.ArrayList;

public class Player {

    private String name;    //Player's name
    private int score = 0;  //Player's current score
    private char colour;    //Colour of player's pieces
    private ArrayList<Block> pieces = new ArrayList<Block>();   //ArrayList containing all the pieces player currently has

    //constructor
    public Player(String name, char colour){
        this.name = name;
        this.colour = colour;
    }

    //toString method for Player which displays their name, the colour they're playing as and their score
    public String toString(){
        return name + " (" + colour + ") " + " score: " + score;
    }

    //mutator and accessor methods
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Block> getPieces() {
        return pieces;
    }

    public char getColour() {
        return colour;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
