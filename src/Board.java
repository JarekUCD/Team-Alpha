import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Board {

    private char[][] layout = new char[14][14]; //2D array for the board
    private Block validMove; //A block that was checked to be valid

    Board(){
        // Blokus Duo has 196 squares which is 14x14

        //Create array. Fill with blank symbol
        for (int i = 0; i< 14; i++){
            for (int j = 0; j<14; j++){
                layout[i][j] = '·';
            }
        }
        //Starting locations
        layout[4][4] = 'O';
        layout[9][9] = 'O';
    }

    @Override
    public String toString() {
        // This prints out the board, with the appropriate numbering on the columns/rows

        String s = "\n";
        StringBuilder b = new StringBuilder(s);

        for (int i = 0; i < 14; i++) {

            //y index numbering (including some formatting based on no of digits)
            b.append(14 - i);
            if (14 - i < 10){
                b.append(" ");
            }

            //Displaying contents of array
            for (int j = 0; j < 14; j++) {
                b.append(" ").append(layout[i][j]).append(" ");
            }
            //b.append("\n").append(s);
            b.append(s);

        }

        // horizontal axis letters.
        b.append("   ");
        char c;
        for(c='A'; c <= 'N'; c++){
            b.append(c + "  ");
        }
        return b.toString();
    }

    public boolean place(int x, int y, int rotations, char colour){
        int i , j;

        validMove.rotate(rotations);
        int[] v = validMove.getPivot();

        for(i = 0; i < validMove.getLength(); i++){
            for(j = 0; j <  validMove.getLength(); j++){
                if(!(validMove.getShape()[i][j] == ' ')){
                    layout[y + (i - v[0])][x + (j - v[1])] = colour;
                }
            }
        }
        return true;
    }

    //Method which checks if player's move is valid
    public boolean isMoveValid(int xInput, int yInput, int rotations, String pieceName, Player player, boolean ft) {
        int i, j;
        boolean stPt = false;

        //check that the coordinates are on the board
        if (xInput >= 14 || yInput >= 14 || xInput < 0 || yInput < 0){
            System.out.println("Invalid move! Try again");
            return false;
        }

        Block chosenPiece = findBlockByName(pieceName, player.getPieces());

        //check if the player has the piece
        if (chosenPiece == null){
            System.out.println("Invalid move! Try again");
            return false;
        }

        //Checks if any part of the block is covering a space that is occupied
        //Try catch block checks if any part of the block is off the bored
        try{
            chosenPiece.rotate(rotations);
            int[] v = chosenPiece.getPivot();

            for(i = 0; i < chosenPiece.getLength(); i++){
                for(j = 0; j <  chosenPiece.getLength(); j++){
                    if(!(chosenPiece.getShape()[i][j] == ' ')){
                        if(layout[yInput + (i - v[0])][xInput + (j - v[1])] == 'B' || layout[yInput + (i - v[0])][xInput + (j - v[1])] == 'W'){
                            System.out.println("Invalid move! Try again");
                            return false;
                        }
                        else if(layout[yInput + (i - v[0])][xInput + (j - v[1])] == 'O'){ //Checks if the block is on a starting place
                            stPt = true;
                        }
                    }
                }
            }
        } catch(Exception e){
            System.out.println("Invalid move! Try again");
            return false;
        }

        if(ft && !stPt){//I fit is the first round and a block is not on the starting point, it returns false
            System.out.println("Invalid move! Try again");
            return false;
        }

        //Removes the piece from the list of blocks of the player and records the block to be placed
        player.getPieces().remove(chosenPiece);
        validMove = chosenPiece;
        return true;
    }

    public Block findBlockByName(String pieceName, ArrayList<Block> playerPieces){
        for (Block piece: playerPieces) {
            if (Objects.equals(piece.getName().toUpperCase(), pieceName)){
                return piece;
            }
        }
        return null;
    }

    //Accessor for layout
    public char[][] getLayout() {
        return layout;
    }
}