import java.util.ArrayList;
import java.util.List;

public class Player{

    private String name;            //Player's name
    private int score = 0;          //Player's current score
    private final char colour;      //Colour of player's pieces
    //ArrayList containing all the pieces player currently has
    private ArrayList<Block> pieces = new ArrayList<Block>();


    //Constructor
    public Player(char colour){
        this.colour = colour;
    }

    //toString method for Player which displays their name, the colour they're playing as and their score
    public String toString(){
        return name + " (" + colour + ") ";
    }

    //Mutator and accessor methods
    public String getName() {
        return name;
    }

    public ArrayList<Block> getPieces() {
        return pieces;
    }

    public char getColour() {
        return colour;
    }

    public void setName(String n) {
         name = n;
    }

    public void setPieces(ArrayList<Block> b){
        pieces = b;
    }
}

