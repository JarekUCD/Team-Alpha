package ui.graphics;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import model.*;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class GraphicsUI implements ui.UI {

    String[] playerSymbol = {"X", "O"};
    String[] startingPositionSymbol = {"x", "o"};
    Board board;
    Player[] players;
    Scanner scanner;
    Scanner guiScanner;
    BlokusGame game;
    PipedInputStream in;
    PipedOutputStream out;

    public GraphicsUI(Board board, Player[] players) throws IOException {
        this.board = board;
        this.players = players;
        scanner = new Scanner(System.in);

        // piper stream for receiving ure input via libGDX frontend
        out = new PipedOutputStream();
        in = new PipedInputStream(out);
        guiScanner = new Scanner(in);
    }

    void updateGamepieceSetDisplay(GamepieceSet set) {
        System.out.print("gamepieces: ");
        for (Object s : set.getPieces().keySet()) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    void updatePlayerDisplay(Player player) {
        System.out.print("Player " + player.getName() + " ("+playerSymbol[player.getPlayerNo()]+") ");
        updateGamepieceSetDisplay(player.getGamepieceSet());
    }
    @Override
    public void updateDisplay() {

        System.out.println();
        System.out.println("BLOKUS DUO");
        System.out.println();
        updateBoardDisplay();
        System.out.println();
        for (Player player : players) {
            updatePlayerDisplay(player);
        }
    }

    private void updateBoardDisplay() {
        game.postRunnable(new Runnable() {
            @Override
            public void run() {
                game.updateBoard(board);
            }
        });
    }

    @Override
    public String getPlayerName(Player player) {
        String name = guiScanner.next();
        return name;
    }

    public String getGamepieceChoice(Player player) {
        String gamepieceName;

        System.out.print("Player " + player.getName() + " (" + playerSymbol[player.getPlayerNo()]+") enter the name of the gamepiece you would like to play: ");
        gamepieceName = scanner.next();

        return gamepieceName;
    }

    public void displayGamepiece(Gamepiece gamepiece) {
        String[][] field = {
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}
        };
        int minX,minY,maxX,maxY;
        final int centerX = 5;
        final int centerY = 5;

        for (Location l : gamepiece.getLocations()) {
            if (l.getX()==0 && l.getY() == 0) {
                field[centerX+l.getX()][centerY+l.getY()] = playerSymbol[gamepiece.getPlayerNo()].toLowerCase()+" ";
            } else {
                field[centerX+l.getX()][centerY+l.getY()] = playerSymbol[gamepiece.getPlayerNo()]+" ";
            }

        }

        minX = field.length;
        for (Location l : gamepiece.getLocations()) {
            if ((centerX + l.getX()) < minX) {
                minX = (centerX + l.getX());
            }
        }

        maxX = 0;
        for (Location l : gamepiece.getLocations()) {
            if ((centerX + l.getX()) > maxX) {
                maxX = (centerX + l.getX());
            }
        }

        minY = field[0].length+1;
        for (Location l : gamepiece.getLocations()) {
            if ((centerY + l.getY()) < minY) {
                minY = (centerY + l.getY());
            }
        }

        maxY = 0;
        for (Location l : gamepiece.getLocations()) {
            if ((centerY + l.getY()) > maxY) {
                maxY = (centerY + l.getY());
            }
        }

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                System.out.print(field[x][y]);
            }
            System.out.println();
        }

    }

    public String getGamepieceManipulation() {
        System.out.println("Enter 'r' to rotate, 'f' to flip, or 'p' to place the gamepiece:");
        return scanner.next();
    }
    public Location getPlacementLocation() {
        System.out.println("Enter x and y coordinates on the board:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Location(x,y);
    }

    @Override
    public void noifyBadMove(Move move) {
        System.out.println("Invalid move!");
    }

    public void gamepieceNotFound(String gamepieceName) {
        System.out.println("Cannot find '" + gamepieceName + "' among player gamepieces");
    }

    @Override
    public void displayGameOverMessage() {
        System.out.println();
        System.out.println("GAME OVER!");
    }

    @Override
    public void announcePlayerMakingFirstMove(Player player) {
        game.postRunnable(new Runnable() {
                @Override
                public void run() {
                    game.setPlayScreen();
                    game.showDialog("Player "+ player.getName() + " goes first!");
                }
            });
    }

    public void setBlokusGame(BlokusGame game) {
        this.game = game;
    }

    public PipedOutputStream getPipe() {
        return out;
    }
}
