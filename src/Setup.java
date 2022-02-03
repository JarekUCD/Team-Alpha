import java.util.Scanner;

public class Setup{

    public void setupPieces(){
        Block i1 = new Block(1, 'r');
        i1.setShape(0);
        Block i2 = new Block(2, 'r');
        i2.setShape(0, 2);
        Block i3 = new Block(3, 'r');
        i3.setShape(0,3,6);
        Block i4 = new Block(4, 'r');
        i4.setShape(0, 4, 8, 12);
        Block i5 = new Block(5, 'r');
        i5.setShape(0,5,10,15,20);
        Block Y = new Block(4, 'r');
        Y.setShape(0,1,2,3,5);
        Block N = new Block(4, 'r');
        N.setShape(0,1,2,6,7);
        Block V3 = new Block(2, 'r');
        V3.setShape(0,2,3);
        Block U = new Block(3, 'r');
        U.setShape(0,1,3,6,7);
        Block V5 = new Block(3, 'r');
        V5.setShape(0,3,6,7,8);
        Block Z5 = new Block(3, 'r');
        Z5.setShape(0,1,4,7,8);
        Block X = new Block(3, 'r');
        X.setShape(1,3,4,5,7);
        Block T5 = new Block(3, 'r');
        T5.setShape(0,1,2,4,7);
        Block W = new Block(3, 'r');
        W.setShape(0,3,4,7,8);
        Block P = new Block(3, 'r');
        P.setShape(0,1,3,4,6);
        Block F = new Block(3, 'r');
        F.setShape(1,2,3,4,7);
        Block L4 = new Block(3, 'r');
        L4.setShape(0,1,2,5);
        Block T4 = new Block(3, 'r');
        T4.setShape(0,1,2,4);
        Block Z4 = new Block(3, 'r');
        Z4.setShape(0,1,4,5);
        Block L5 = new Block(4, 'r');
        L5.setShape(0, 1, 2, 3, 7);

        System.out.println("i1 = ");
        i1.printShape();
        System.out.println("L5 = ");
        L5.printShape();
        System.out.println("Y = ");
        Y.printShape();
        System.out.println("N = ");
        N.printShape();
        System.out.println("V3 = ");
        V3.printShape();
        System.out.println("U = ");
        U.printShape();
        System.out.println("V5 = ");
        V5.printShape();
        System.out.println("Z5 = ");
        Z5.printShape();
        System.out.println("X = ");
        X.printShape();
        System.out.println("T5 = ");
        T5.printShape();
        System.out.println("W = ");
        W.printShape();
        System.out.println("P = ");
        P.printShape();
        System.out.println("F = ");
        F.printShape();
        System.out.println("L4 = ");
        L4.printShape();
        System.out.println("T4 = ");
        T4.printShape();
        System.out.println("Z4 = ");
        Z4.printShape();

    }

    public void setupPlayers(){
        Scanner names = new Scanner(System.in);
        System.out.println("Player 1, please enter your name:");
        Player player1 = new Player(names.nextLine(), 'b');
        System.out.println("Player 2, please enter your name:");
        Player player2 = new Player(names.nextLine(), 'w');
        System.out.println("Player 1 is " + player1);
        System.out.println("Player 2 is " + player2);

    }

}
