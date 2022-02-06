//Team name: alpha, Student Numbers: 20377771 20709465 20394476
public class Main {

    //Used to start up the whole program
    public static void main(String[] args){
        Setup s = new Setup();
        s.setupPieces();

        Game game = new Game(s);
        System.out.println("Welcome to Blokus Duo!\n ");
        game.run();
    }
}
