import java.util.ArrayList;
import java.util.Objects;

public class Board {

    private char[][] layout = new char[14][14];

    public Board(){
        // Blokus Duo has 196 squares which is 14x14

        //Create array. Fill with blank symbol
        for (int i = 0; i< 14; i++){
            for (int j = 0; j<14; j++){
                layout[i][j] = '·';
            }
        }
        layout[9][4] = 'O';
        layout[4][9] = 'O';
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

        //x index numbering (including some formatting based on no of digits)
        b.append("   ");
        for (int i = 1; i < 15; i++) {
            b.append(i);
            if (i<9){
                b.append("  ");
            } else {
                b.append(" ");
            }
        }
        return b.toString();
    }

    public void place(int x, int y, int r, String n, Player p){
        int c, i , j;

        for(c = 0; c < p.getPieces().size(); c++){
            if(n == p.getPieces().get(c).getName()){
                break;
            }
        }

        p.getPieces().get(c).rotate(r);

        for(i = 0; i < p.getPieces().get(c).getLength(); i++){
            for(j = 0; j <  p.getPieces().get(c).getLength(); j++){
                layout[y + i][x + j] = p.getPieces().get(c).getShape()[i][j];
            }
        }

        //Remove piece from ArrayList at index c
        p.getPieces().remove(c);

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

        return true;
    }

    public Block findBlockByName(String pieceName, ArrayList<Block> playerPieces){
        for (Block piece: playerPieces) {
            if (Objects.equals(piece.getName(), pieceName)){
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