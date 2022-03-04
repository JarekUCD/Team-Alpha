//Team name: alpha, Student Numbers: 20377771 20709465 20394476
import java.util.Scanner;

public class Game{

    Setup sp;
    boolean p1gameOver = false, p2gameOver = false;
    boolean firstMove = true;
    Block inUse;

    public Game(Setup s){
        sp = s;
    }


    public void run() {
        sp.setupPlayers();

        //Runs the first turn for each player(which requires them starting on a specific point)
        //firstMove increases by one after each of the first turns to show what stage of the first two moves the program is at
        turn(sp.getPlayer1());
        turn(sp.getPlayer2());

        firstMove = false;

        while (!p1gameOver || !p2gameOver) {  //Continues while both players have valid moves.
            if (!p1gameOver){p1gameOver = turn(sp.getPlayer1());}  //If player has another valid move, they get a turn.
            if (!p2gameOver){p2gameOver = turn(sp.getPlayer2());}
        }
        calculateScore();
    }

    public boolean turn(Player p) {
        int x, y;
        String n;

        boolean availableMoves = sp.getBoard().checkAllValidMoves(p);  // THIS FUNCTION SHOULD BE CALLED TO CHECK FOR ALL THE VALID MOVES. IF A PLAYER HAS NONE, THIS WILL PASS PLAY TO OTHER PLAYER
        if (!availableMoves && !firstMove){
            System.out.println(sp.getBoard().errorMessage());
            return true;
        }

        System.out.println(sp.getBoard());
        System.out.println(p.getName() + "'s pieces: " + p.getPieces());

        System.out.println(p + ", it's your turn");

        while(true){
            System.out.println("What piece would you like to use?");
            n = sp.scan.next();
            System.out.println(n);
            inUse = sp.getBoard().findBlockByName(n.toUpperCase(), p.getPieces());
            inUse.resetShape();
            if(inUse == null){
                System.out.println("There is no piece by that name that can be used");
            }
            else {
                inUse.printShape();
                break;
            }
        }
        while(true){
            System.out.println("What would you like to do with this piece? (f to flip, r to rotate or p to place)");
            n = sp.scan.next();
            switch (n.charAt(0)) {
                case 'f' -> {
                    inUse.flip();
                    inUse.printShape();
                }
                case 'r' -> {
                    inUse.rotate(1);
                    inUse.printShape();
                }
                case 'p' -> {
                    x = sp.scan.nextInt();
                       y = sp.scan.nextInt();
                    if (!sp.getBoard().isMoveValid(x, (13 - y), inUse, p, firstMove)) {  //Checks for valid move.
                        System.out.println(sp.getBoard().errorMessage());
                    } else {

                        sp.getBoard().place(x, (13 - y), inUse, p);
                        if (inUse.name.equals("I1") && p.getPieces().size() == 0){
                            p.setScore(50);
                        }
                        return false;
                    }
                }
            }
        }
    }

    //Method to calculate the player's score
    public void calculateScore(){
        int scoreX = sp.getPlayer1().getScore(), scoreO = sp.getPlayer2().getScore();

        char[][] layout = sp.getBoard().getLayout();

        //Iterates through the array and counts the number of blocks on the board occupied by each player
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (layout[i][j] == 'X'){
                    scoreX++;
                }
                else if (layout[i][j] == 'O'){
                    scoreO++;
                }
            }
        }

        System.out.println(sp.getBoard() + "\n");

        System.out.println(sp.getPlayer1() + " got "+ scoreX + " points, " + sp.getPlayer2() + " got "+ scoreO + " points.");
        if(scoreX > scoreO){
            System.out.println(sp.getPlayer1() + " wins!");
        }
        else if(scoreO > scoreX){
            System.out.println(sp.getPlayer2() + " wins!");
        }
        else{
            System.out.println("It's a draw!");
        }

    }


}

