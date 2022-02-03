import java.util.Random;
import java.util.Scanner;

public class Main {

    public boolean gameOver = false;
    public boolean firstTurn = true;

    public int l = 4;
    public char[][] a = new char[l][l];

    //Used to start up the whole program
    public static void main(String[] args){

        System.out.println("Welcome to Blokus Duo!\n ");

        // Blokus Duo has 196 squares which is 14x14
        Board board = new Board();
        System.out.println(board);

        Setup setup = new Setup();
        setup.setupPieces();
        setup.setupPlayers();

    }
}
