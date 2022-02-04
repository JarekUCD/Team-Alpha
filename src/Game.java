import java.util.Scanner;

public class Game {

    Setup sp;

    public void run(){
        String input;

        boolean firstTurn = true, gameOver = false;
        Scanner scan = new Scanner(System.in);
        sp.setupPlayers();

        while (firstTurn) {
            System.out.println(sp.board);
            System.out.println("Type your move");
            input = scan.nextLine();
        }
        while (!gameOver) {
            //TODO
        }
    }

    public Game(Setup s) {
        sp = s;
    }
}

