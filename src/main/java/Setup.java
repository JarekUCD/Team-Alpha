//Team name: alpha, Student Numbers: 20377771 20709465 20394476
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Setup{
    private Player player2 = new Player('O');
    private Player player1 = new Player('X');
    private int firstPlayer;
    private Board board = new Board();
    private ArrayList<Block> blocks = new ArrayList<>();
    public Scanner scan;

    //To fill original arraylist
    public void setupPieces(){
        blocks.add(new Block(1, "I1"));
        blocks.get(0).setShape(0);
        blocks.add(new Block(2, "I2"));
        blocks.get(1).setShape(4, 0);
        blocks.add(new Block(3, "I3"));
        blocks.get(2).setShape(8,0, 4);
        blocks.add(new Block(4, "I4"));
        blocks.get(3).setShape(12, 0, 4, 8);
        blocks.add(new Block(5, "I5"));
        blocks.get(4).setShape(16,0, 4, 8, 12);
        blocks.add(new Block(4, "Y"));
        blocks.get(5).setShape(5, 1, 4, 6, 7);
        blocks.add(new Block(4, "N"));
        blocks.get(6).setShape(1, 2, 3, 4, 5);
        blocks.add(new Block(2, "V3"));
        blocks.get(7).setShape(4, 0, 5);
        blocks.add(new Block(3, "U"));
        blocks.get(8).setShape(4, 0, 1, 8, 9);
        blocks.add(new Block(3, "V5"));
        blocks.get(9).setShape(8, 0, 4, 9, 10);
        blocks.add(new Block(3, "Z5"));
        blocks.get(10).setShape(5, 2, 4, 6, 8);
        blocks.add(new Block(3, "X"));
        blocks.get(11).setShape(5, 1, 4, 6, 9);
        blocks.add(new Block(3, "T5"));
        blocks.get(12).setShape(9, 1, 5, 8, 10);
        blocks.add(new Block(3, "W"));
        blocks.get(13).setShape(5, 1, 2, 4, 8);
        blocks.add(new Block(3, "P"));
        blocks.get(14).setShape(0, 1, 4, 5, 8);
        blocks.add(new Block(3, "F"));
        blocks.get(15).setShape(5, 1, 2, 4, 9);
        blocks.add(new Block(3, "L4"));
        blocks.get(16).setShape(8, 0, 4, 9);
        blocks.add(new Block(3, "T4"));
        blocks.get(17).setShape(5, 1, 4, 6);
        blocks.add(new Block(3, "Z4"));
        blocks.get(18).setShape(5, 1, 2, 4);
        blocks.add(new Block(4, "L5"));
        blocks.get(19).setShape(4, 0, 5, 6, 7);
        blocks.add(new Block(2, "O4"));
        blocks.get(20).setShape(4, 0, 1, 5);
    }

    public void setFirstPlayer(int firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setupPlayers() {
        String name1, name2;

        setupPieces();
        player1.setPieces(blocks);
        player2.setPieces(blocks);

        scan = new Scanner(System.in);
        System.out.println("Player 1, please enter your name:");
        name1 = scan.nextLine();
        System.out.println("Player 2, please enter your name:");
        name2 = scan.nextLine();

        if(firstPlayer == 1){
            player1.setStart(1);
            player1.setName(name1);
            player2.setStart(2);
            player2.setName(name2);
        }
        else if(firstPlayer == 2) {
            player1.setStart(2);
            player1.setName(name2);
            player2.setStart(1);
            player2.setName(name1);
        }
        System.out.println("Player 1 is " + player1);
        System.out.println("Player 2 is " + player2);
        System.out.println("Player " + firstPlayer + " goes first!");

    }

    public String getInput() {
        return scan.next();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Board getBoard() {
        return board;
    }
}
