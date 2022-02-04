import java.util.Random;
import java.util.Scanner;

public class Main {

    //Used to start up the whole program
    public static void main(String[] args){
        Setup s = new Setup();
        Game game = new Game(s);
        System.out.println("Welcome to Blokus Duo!\n ");
        game.run();
    }
}
