import java.util.ArrayList;
import java.util.List;

public class Player{

    private final String name;      //Player's name
    private int score = 0;          //Player's current score
    private final char colour;      //Colour of player's pieces
    //ArrayList containing all the pieces player currently has
    private ArrayList<String> pieces = new ArrayList<String>(List.of("i1", "i2", "i3", "i4", "i5", "Y", "N", "X", "U",
            "W", "P", "F", "V3", "V5", "Z4", "Z5", "T4", "T5", "L4", "L5", "O4"));


    //Constructor
    public Player(String name, char colour){
        this.name = name;
        this.colour = colour;
    }

    //toString method for Player which displays their name, the colour they're playing as and their score
    public String toString(){
        return name + " (" + colour + ") " + " score: " + score;
    }

    //Method to calculate the player's score
    public int calculateScore(Board board){

        char[][] layout = board.getLayout();

        //Iterates through the array and counts the number of blocks on the board occupied by the player
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (layout[i][j] == colour){
                    score++;
                }
            }
        }
        return score;
    }

    //Mutator and accessor methods
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<String> getPieces() {
        return pieces;
    }

    public char getColour() {
        return colour;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

