import java.util.ArrayList;
import java.util.Scanner;

public class Setup{
    Player player2 = new Player('W');
    Player player1 = new Player('B');
    Board board = new Board();
    ArrayList<Block> blocks = new ArrayList<>();

    public void setupPieces(){
        blocks.add(new Block(1, 'r', "i1"));
        blocks.get(0).setShape(0);
        blocks.add(new Block(2, 'r', "i2"));
        blocks.get(1).setShape(0, 2);
        blocks.add(new Block(3, 'r', "i3"));
        blocks.get(2).setShape(0,3,6);
        blocks.add(new Block(4, 'r', "i4"));
        blocks.get(3).setShape(0, 4, 8, 12);
        blocks.add(new Block(5, 'r', "i5"));
        blocks.get(4).setShape(0,5,10,15,20);
        blocks.add(new Block(4, 'r', "Y"));
        blocks.get(5).setShape(0,1,2,3,5);
        blocks.add(new Block(4, 'r', "N"));
        blocks.get(6).setShape(0,1,2,6,7);
        blocks.add(new Block(2, 'r', "V3"));
        blocks.get(7).setShape(0,2,3);
        blocks.add(new Block(3, 'r', "U"));
        blocks.get(8).setShape(0,1,3,6,7);
        blocks.add(new Block(3, 'r', "V5"));
        blocks.get(9).setShape(0,3,6,7,8);
        blocks.add(new Block(3, 'r', "Z5"));
        blocks.get(10).setShape(0,1,4,7,8);
        blocks.add(new Block(3, 'r', "X"));
        blocks.get(11).setShape(1,3,4,5,7);
        blocks.add(new Block(3, 'r', "T5"));
        blocks.get(12).setShape(0,1,2,4,7);
        blocks.add(new Block(3, 'r', "W"));
        blocks.get(13).setShape(0,3,4,7,8);
        blocks.add(new Block(3, 'r', "P"));
        blocks.get(14).setShape(0,1,3,4,6);
        blocks.add(new Block(3, 'r', "F"));
        blocks.get(15).setShape(1,2,3,4,7);
        blocks.add(new Block(3, 'r', "L4"));
        blocks.get(16).setShape(0,1,2,5);
        blocks.add(new Block(3, 'r', "T4"));
        blocks.get(17).setShape(0,1,2,4);
        blocks.add(new Block(3, 'r', "Z4"));
        blocks.get(18).setShape(0,1,4,5);
        blocks.add(new Block(4, 'r', "L5"));
        blocks.get(19).setShape(0, 1, 2, 3, 7);
    }

    public void setupPlayers(){

        setupPieces();
        player1.setPieces(blocks);
        player2.setPieces(blocks);

        Scanner names = new Scanner(System.in);
        System.out.println("Player 1, please enter your name:");
        player1.setName(names.nextLine());
        System.out.println("Player 2, please enter your name:");
        player2.setName(names.nextLine());
        System.out.println("Player 1 is " + player1);
        System.out.println("Player 2 is " + player2);

    }

}
