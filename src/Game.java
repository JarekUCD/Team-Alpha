import java.util.Scanner;

public class Game {

    Setup sp;

    public void run(){
        boolean firstTurn = true, gameOver = false;
        sp.setupPlayers();

        while (firstTurn) {
            System.out.println(sp.board);
            turn(firstTurn);
        }
        while (!gameOver) {
            //TODO
        }
    }



    public Game(Setup s) {
        sp = s;
    }

    public void turn(boolean ft){
        int x, y, r;
        String n;

        System.out.println(sp.player1 + ", type your move");
        Scanner scan = new Scanner(System.in);

        x = scan.nextInt();
        y = scan.nextInt();
        r = scan.nextInt();
        n = scan.next();

        System.out.println(x + " " + y + " " + r + " " + n);
    }

    //Method to calculate the player's score
    public int calculateScore(Board board, char colour){
        int score = 0;

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
}

