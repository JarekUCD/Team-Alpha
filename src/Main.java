import java.util.Random;
import java.util.Scanner;

public class Main {

    //Used to start up the whole program
    public static void main(String[] args){
        Setup s = new Setup();
        s.setupPieces();

        /*
        To show all the shapes
        s.blocks.get(0).printName();
        s.blocks.get(0).printShape();
        s.blocks.get(1).printName();
        s.blocks.get(1).printShape();
        s.blocks.get(2).printName();
        s.blocks.get(2).printShape();
        s.blocks.get(3).printName();
        s.blocks.get(3).printShape();
        s.blocks.get(4).printName();
        s.blocks.get(4).printShape();
        s.blocks.get(5).printName();
        s.blocks.get(5).printShape();
        s.blocks.get(6).printName();
        s.blocks.get(6).printShape();
        s.blocks.get(7).printName();
        s.blocks.get(7).printShape();
        s.blocks.get(8).printName();
        s.blocks.get(8).printShape();
        s.blocks.get(9).printName();
        s.blocks.get(9).printShape();
        s.blocks.get(10).printName();
        s.blocks.get(10).printShape();
        s.blocks.get(11).printName();
        s.blocks.get(11).printShape();
        s.blocks.get(12).printName();
        s.blocks.get(12).printShape();
        s.blocks.get(13).printName();
        s.blocks.get(13).printShape();
        s.blocks.get(14).printName();
        s.blocks.get(14).printShape();
        s.blocks.get(15).printName();
        s.blocks.get(15).printShape();
        s.blocks.get(16).printName();
        s.blocks.get(16).printShape();
        s.blocks.get(17).printName();
        s.blocks.get(17).printShape();
        s.blocks.get(18).printName();
        s.blocks.get(18).printShape();
        s.blocks.get(19).printName();
        s.blocks.get(19).printShape();
        s.blocks.get(20).printName();
        s.blocks.get(20).printShape();
        */
        
        Game game = new Game(s);
        System.out.println("Welcome to Blokus Duo!\n ");
        game.run();
    }
}
