import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;


//Team name: alpha, Student Numbers: 20377771 20709465 20394476
public class Main {

    //Used to start up the whole program
    public static void main(String[] args) throws IOException {
        System.out.println("Updated version C");
        Random rd = new Random();
        int firstPlayer = -1;  //Intellij insists this must be initialised first.

        //If an argument is placed in the command line, this will affect the starting player.
        if (args.length > 0) {
            for (String currentArg : args) {
                if (currentArg.equalsIgnoreCase("-X")) {
                    firstPlayer = 1;
                } else if (currentArg.equalsIgnoreCase("-O")) {
                    firstPlayer = 2;
                } else if (currentArg.equalsIgnoreCase("-gui")) {
                    System.out.println("Gui config detected");  // TEMP. WILL BE USED TO ALLOW GUI RECOGNITION
                } else if (currentArg.contains(".txt")) {
                    System.setIn(new FileInputStream(currentArg)); //If there is a text file, this reads it in as if it is user input
                }
            }
        }
        else firstPlayer = rd.nextInt(2)+1; // choosing a random number between 1 and 2

        Setup s = new Setup();
        s.setFirstPlayer(firstPlayer);

        Game game = new Game(s);
        System.out.println("Welcome to Blokus Duo!\n ");
        game.run();
    }
}
