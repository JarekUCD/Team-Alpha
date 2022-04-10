package model;

import ui.UI;

import java.util.HashSet;
import java.util.Set;

public abstract class Player {
    private GamepieceSet pieces;
    private String name;
    private int playerNo;

    public Player(int playerNo) {
        this.pieces = new GamepieceSet(playerNo);
        this.playerNo = playerNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public GamepieceSet getGamepieceSet() {
        return pieces;
    }

    public abstract Move makeMove(Board board);

    public abstract void setUI(UI ui);

    // Test methods

    @Override
    public String toString() {
        return super.toString()+"(name="+getName()+",playerNo="+getPlayerNo()+",gamepieceSet="+getGamepieceSet()+")";
    }
}
