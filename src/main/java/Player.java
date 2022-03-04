//Team name: alpha, Student Numbers: 20377771 20709465 20394476
import java.util.ArrayList;

public class Player{

    public int start;               //Start position of player
    private String name;            //Player's name
    private int score = 0;          //Player's current score
    private final char colour;      //Colour of player's pieces
    //ArrayList containing all the pieces the player currently has
    private ArrayList<Block> pieces = new ArrayList<>();


    //Constructor
    public Player(char colour){
        this.colour = colour;
    }

    //toString method for Player which displays their name, the colour they're playing as and their score
    public String toString(){
        return name + " (" + colour + ") ";
    }

    //Mutator and accessor methods

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

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
        pieces.addAll(b);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

