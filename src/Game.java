import java.util.Scanner;

public class Game {

    Setup sp;
    boolean firstTurn = true, gameOver = false;

    public void run(){
        sp.setupPlayers();

        while (!gameOver) {
            System.out.println(sp.board);
            turn(sp.player1);
            System.out.println(sp.board);
            turn(sp.player2);
            firstTurn = false;
        }
        calculateScore();
    }



    public Game(Setup s) {
        sp = s;
    }

    public void turn(Player p) {
        int x =-1, y = -1, r = -1;
        String n = "";


        System.out.println(p.getName() + "'s pieces: " + p.getPieces());

        while(true){
            System.out.println(p + ", type your move, using co-ordinates, followed by the number of rotations then the block name. e.g J5 2 i5");

            //This block takes user co-ordinates. The try-catch prevents an error if the format is invalid.
            while(true){
                Scanner scan = new Scanner(System.in);
                try{
                    char temp = scan.next().charAt(0);
                    x = (int)temp-65;
                    y = scan.nextInt() - 1;
                    r = scan.nextInt();
                    n = scan.next();
                    break;
                } catch (Exception e) {
                    System.out.println("Sorry, invalid format. Please try again using the co-ordinates, followed by the number of rotations then the block name. e.g J5 2 i5");
                    scan.reset();
                }
            }

            if (firstTurn && (!(x == 4 && y == 9) && !(x == 9 && y == 4))) {   // Prevents placement outside of starting location
                System.out.println("Invalid move! Please pick one of the starting locations.");
            } else if (sp.board.isMoveValid(x, y, r, n, p)) {  //Checks for valid move.
                sp.board.place(x, y, r, p.getColour());
                break;
            } else{
                System.out.println("Invalid move! Try again");
            }
        }
    }

    //Method to calculate the player's score
    public void calculateScore(){
        int scoreB = 0, scoreW = 0;

        char[][] layout = sp.board.getLayout();

        //Iterates through the array and counts the number of blocks on the board occupied by each player
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (layout[i][j] == 'B'){
                    scoreB++;
                }
                else if (layout[i][j] == 'W'){
                    scoreW++;
                }
            }
        }

        System.out.println(sp.player1 + " got "+ scoreB + " points, " + sp.player2 + " got "+ scoreW + " points.");
        if(scoreB > scoreW){
            System.out.println(sp.player1 + " wins!");
        }
        else if(scoreW > scoreB){
            System.out.println(sp.player2 + " wins!");
        }
        else{
            System.out.println("It's a draw!");
        }
    }

}

