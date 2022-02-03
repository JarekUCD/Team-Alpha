public class Main {

    public static void main(String[] args){

        System.out.println("Welcome to Blokus Duo!\n ");
        Board board = new Board();

        // Blokus Duo has 196 squares which is 14x14
        System.out.println(board);

        Setup setup = new Setup();
        setup.setupPieces();
        setup.setupPlayers();

    }
}
