import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Board {

    private char[][] layout = new char[14][14];
    private Block validMove;

    Board(){
        // Blokus Duo has 196 squares which is 14x14

        //Create array. Fill with blank symbol
        for (int i = 0; i< 14; i++){
            for (int j = 0; j<14; j++){
                layout[i][j] = '·';
            }
        }
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

    public void place(int x, int y, int rotations, char colour){
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
    }

    //Method which checks if player's move is valid
    public boolean isMoveValid(int xInput, int yInput, int rotations, String pieceName, Player player) {

        //check that the coordinates are on the board
        if (xInput >= 14 || yInput >= 14){
            return false;
        }

        Block chosenPiece = findBlockByName(pieceName, player.getPieces());

        //check if the player has the piece
        if (chosenPiece == null){
            return false;
        }



        //check that the place on the board is not already occupied
        //TODO
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