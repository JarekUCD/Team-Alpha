//Team name: alpha, Student Numbers: 20377771 20709465 20394476
import java.util.ArrayList;
import java.util.Objects;

public class Board {

    private char[][] layout = new char[14][14]; //2D array for the board
    private int errorMessageType = 0; //displays specific error message, 0 if no error

    Board(){
        // Blokus Duo has 196 squares which is 14x14

        //Create array. Fill with blank symbol
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                layout[i][j] = '.';
            }
        }
        //Starting locations
        layout[4][4] = '1';
        layout[9][9] = '2';
    }

    @Override
    public String toString() {
        // This prints out the board, with the appropriate numbering on the columns/rows

        int i;
        String s = "\n";
        StringBuilder b = new StringBuilder(s);

        for (i = 0; i < 14; i++) {

            //y index numbering (including some formatting based on no of digits)
            b.append(13 - i);
            if (13 - i < 10){
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
        for (i = 0; i < 14; i++) {
            b.append(i);
            b.append(" ");
            if (i < 10) {
                b.append(" ");
            }
        }
        return b.toString();
    }

    public void place(int x, int y, Block piece, Player player) {
        int i, j;
        int[] v = piece.getPivot();
        char colour = player.getColour();

        for (i = 0; i < piece.getLength(); i++) {
            for (j = 0; j < piece.getLength(); j++) {
                if (!(piece.getShape()[i][j] == ' ')) {
                    layout[y + (i - v[0])][x + (j - v[1])] = colour;
                }
            }
        }
        piece.resetShape();

        //Removes the piece from the list of blocks of the player and records the block to be placed
        player.getPieces().remove(piece);
    }

    // Method which checks if player's move is valid
    public boolean isMoveValid(int xInput, int yInput, Block chosenPiece, Player player, boolean firstMove) {

        setErrorMessageType(0);

        //check that the coordinates are on the board
        if (xInput >= 14 || yInput >= 14 || xInput < 0 || yInput < 0) {
            setErrorMessageType(1);
            return false;
        }

        int[] pivot = chosenPiece.getPivot();

        //checks that the piece will not overlap with other pieces already on the board
        if (!checkPieceDoesntOverlap(chosenPiece, xInput, yInput, pivot)){
            return false;
        }

        //checks that if this is the player's first move that the piece covers one of the starting points
        if(firstMove){
            return checkFirstMove(chosenPiece, xInput, yInput, pivot, player.getStart());
        }

        //checks that the piece does not directly touch pieces of the same colour
        if (!(checkOwnPiecesTouching(chosenPiece, xInput, yInput, player.getColour(), pivot))){
            setErrorMessageType(5);
            return false;
        }

        //checks that there is another of the player's pieces on the diagonal
        if(!(checkMoveHasDiagonal(chosenPiece, xInput, yInput, player.getColour(), pivot))){
            setErrorMessageType(6);
            return false;
        }

        return true;
    }

    //Returns false if any part of the block is covering a space that is occupied
    //Try catch block checks if any part of the block is off the board
    private boolean checkPieceDoesntOverlap(Block chosenPiece, int xInput, int yInput, int[] pivot){
        try {
            for (int i = 0; i < chosenPiece.getLength(); i++) {
                for (int j = 0; j < chosenPiece.getLength(); j++) {
                    if (!(chosenPiece.getShape()[i][j] == ' ')) {
                        if (layout[yInput + (i - pivot[0])][xInput + (j - pivot[1])] == 'X' || layout[yInput + (i - pivot[0])][xInput + (j - pivot[1])] == 'O') {
                            setErrorMessageType(2);
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            setErrorMessageType(3);
            return false;
        }

        return true;
    }

    //Checks through the chosen piece 2d array for corners
    private boolean checkMoveHasDiagonal(Block chosenPiece, int xInput, int yInput, char playerColour, int[] pivot){

        for (int i = 0; i < chosenPiece.getLength(); i++) {
            for (int j = 0; j < chosenPiece.getLength(); j++) {
                if (chosenPiece.getShape()[i][j] != ' ') {
                    if (iterateCorners(chosenPiece, xInput, yInput, playerColour, i, j, pivot)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Helper method for checkMoveHasDiagonal
    private boolean iterateCorners(Block chosenPiece, int xInput, int yInput, char playerColour, int row, int column, int[] pivot){

        int diagonalRow;
        int diagonalColumn;

        for (int i = 1; i < 5; i++) {
            switch (i){
                case 1:
                    //check top right diagonal
                    diagonalRow = row - 1;
                    diagonalColumn = column + 1;
                    break;
                case 2:
                    //check bottom right diagonal
                    diagonalRow = row + 1;
                    diagonalColumn = column + 1;
                    break;
                case 3:
                    //check bottom left diagonal
                    diagonalRow = row + 1;
                    diagonalColumn = column - 1;
                    break;
                case 4:
                default:
                    //check top left diagonal
                    diagonalRow = row - 1;
                    diagonalColumn = column - 1;
            }

            try {
                //checks there is a diagonal on the board to the space which is occupied by the same colour
                if (layout[yInput + (diagonalRow - pivot[0])][xInput + (diagonalColumn - pivot[1])] == playerColour){
                    try {
                        //checks that this diagonal is touching an outer corner of the piece
                        //this is true if 1 or both of the spaces on either side of the corner are
                        //a. empty
                        //b. go outside the bounds of the shape array (therefore empty)
                        if (chosenPiece.getShape()[diagonalRow][column] == ' '){
                            try {
                                if (chosenPiece.getShape()[row][diagonalColumn] == ' '){
                                    return true;
                                }
                            } catch (ArrayIndexOutOfBoundsException e){
                                return true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        try {
                            if (chosenPiece.getShape()[row][diagonalColumn] == ' '){
                                return true;
                            }
                        } catch (ArrayIndexOutOfBoundsException f){
                            return true;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored){}

        }
        return false;
    }

    //checks all 4 sides of piece that it is not touching piece of same colour
    //ignores if out of bounds
    private boolean checkOwnPiecesTouching(Block chosenPiece, int xInput, int yInput, char playerColour, int[] pivot){

        for (int i = 0; i < chosenPiece.getLength(); i++) {
            for (int j = 0; j < chosenPiece.getLength(); j++) {
                if (chosenPiece.getShape()[i][j] != ' '){
                    for (int k = 1; k < 5; k++) {
                        switch (k) {
                            case 1:
                                try {
                                    //check above
                                    if (layout[yInput + (i + 1 - pivot[0])][xInput + (j - pivot[1])] == playerColour) {
                                        return false;
                                    } else break;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    break;
                                }
                            case 2:
                                //check below
                                try {
                                    if (layout[yInput + (i - 1 - pivot[0])][xInput + (j - pivot[1])] == playerColour) {
                                        return false;
                                    } else break;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    break;
                                }
                            case 3:
                                //check left
                                try {
                                    if (layout[yInput + (i - pivot[0])][xInput + (j - 1 - pivot[1])] == playerColour) {
                                        return false;
                                    } else break;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    break;
                                }
                            case 4:
                            default:
                                //check right
                                try {
                                    if (layout[yInput + (i - pivot[0])][xInput + (j + 1 - pivot[1])] == playerColour) {
                                        return false;
                                    } else break;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    break;
                                }
                        }
                    }
                }
            }
        }

        return true;
    }

    //checks that if this is the player's first move that the piece covers one of the starting points
    private boolean checkFirstMove(Block chosenPiece, int xInput, int yInput, int[] pivot, int pos){
        int xCheck, yCheck;

        if (pos == 1){
            xCheck = 4;
            yCheck = 4;
        }
        else {
            xCheck = 9;
            yCheck = 9;
        }

        try {
            for (int i = 0; i < chosenPiece.getLength(); i++) {
                for (int j = 0; j < chosenPiece.getLength(); j++) {
                    if (!(chosenPiece.getShape()[i][j] == ' ')) {
                        if ((yInput + (i - pivot[0]) == xCheck) && (xInput + (j - pivot[1]) == yCheck)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            setErrorMessageType(4);
            return false;
        }
        setErrorMessageType(4);
        return false;
    }



    //Assigns a specific error message for the user depending on what is wrong with their input
    public String errorMessage() {
        switch (getErrorMessageType()) {
            case 1:
                setErrorMessageType(0);
                return "Invalid coordinates entered!\n Try again";
            case 2:
                setErrorMessageType(0);
                return """
                        Invalid move
                        Your piece overlaps with a piece already on the board
                        Try again""";
            case 3:
                setErrorMessageType(0);
                return """
                        Invalid move
                        Your piece goes outside the bounds of the board
                        Try again""";
            case 4:
                setErrorMessageType(0);
                return """
                        Invalid move
                        Make sure that your piece covers one of the starting points, e 10 or j 5
                        Try again""";
            case 5:
                setErrorMessageType(0);
                return """
                        Invalid move
                        Make sure that your piece doesn't directly touch any of your other pieces
                        Try again""";
            case 6:
                setErrorMessageType(0);
                return """
                        Invalid move
                        The corner of your piece must touch at least one other piece of the same colour
                        Try again""";
            case 7:
                setErrorMessageType(0);
                return """
                        You do not have any valid moves this turn
                        """;
            default:
                setErrorMessageType(0);
                return null;
        }
    }

    //Returns the Block object corresponding to the name entered by the user in their list of pieces
    //If the block is not contained in their list of pieces then the method returns null
    public Block findBlockByName(String pieceName, ArrayList<Block> playerPieces) {
        for (Block piece : playerPieces) {
            if (Objects.equals(piece.getName().toUpperCase(), pieceName)) {
                return piece;
            }
        }
        return null;
    }

    //Accessor for layout
    public char[][] getLayout() {
        return layout;
    }

    //After the first move for each player, checks that the player whose turn it is has a valid move for that turn
    public boolean checkAllValidMoves(Player p){
        for (Block piece : p.getPieces()) {             //iterates through the players pieces
            for (int i = 0; i < 14; i++) {              // x-axis
                for (int j = 0; j < 14; j++) {          // y-axis
                    for (int r = 0; r < 4; r++) {       // rotations
                        for (int f = 0; f < 2; f++) {   // flip
                            if (this.isMoveValid(i, j, piece, p, false)){
                                setErrorMessageType(0);
                                return true;
                            }
                            piece.flip();
                        }
                        piece.rotate(1);
                    }
                }
            }
            piece.resetShape();
        }
        setErrorMessageType(7);
        return false;
    }

    public int getErrorMessageType() {
        return errorMessageType;
    }

    public void setErrorMessageType(int errorMessageType) {
        this.errorMessageType = errorMessageType;
    }
}