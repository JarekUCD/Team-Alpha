import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import control.BlokusDuoPlay;
import model.*;

import ui.UI;
import ui.graphics.BlokusGame;
import ui.graphics.GraphicsPlayer;
import ui.graphics.GraphicsUI;
import ui.text.TextPlayer;
import ui.text.TextUI;

import java.io.IOException;
import java.util.Random;


public class Sprint4 {

    public static void main(String[] args) throws IOException {

        // Initialise game

        int activePlayer = new Random().nextInt(2);
        Board board = new Board(activePlayer);
        Player[] players = new Player[Board.startLocations.length];
        UI ui;
        BlokusDuoPlay blokusDuoPlay;
        boolean graphicsUI = true;

        for (String arg : args) {
            switch (arg) {
                case "-X":
                    activePlayer = 0;
                    break;
                case "-O":
                    activePlayer = 1;
                    break;
                case "-text":
                    graphicsUI = false;
            }
        }

        if (graphicsUI) {
            for (int playerNo = 0; playerNo < players.length; playerNo++) {
                players[playerNo] = new GraphicsPlayer(playerNo);
            }
            ui = new GraphicsUI(board, players);
            for (Player player : players) {
                player.setUI(ui);
            }
            blokusDuoPlay = new BlokusDuoPlay(board,players,ui,activePlayer);

            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setWindowSizeLimits( BlokusGame.getVirtualWidth(),
                    BlokusGame.getVirtualHeight(),
                    BlokusGame.getVirtualWidth(),
                    BlokusGame.getVirtualHeight());
            config.setWindowIcon("icon.png");
            config.setTitle("Blokus Duo");
            new Lwjgl3Application(new BlokusGame(blokusDuoPlay), config);

        } else {

            for (int playerNo = 0; playerNo < players.length; playerNo++) {
                players[playerNo] = new TextPlayer(playerNo);
            }
            ui = new TextUI(board, players);
            for (Player player : players) {
                player.setUI(ui);
            }
            blokusDuoPlay = new BlokusDuoPlay(board,players,ui,activePlayer);
            blokusDuoPlay.run();
        }








    }
}
