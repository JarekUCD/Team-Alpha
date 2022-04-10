package model;

public class Move {
    private Player player;
    private String gamepieceName;
    private Gamepiece gamepiece;
    private Location location;

    public Move(Player player, Gamepiece gamepiece, String gamepieceName, Location location) {
        this.player = player;
        this.gamepiece = gamepiece;
        this.gamepieceName = gamepieceName;
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public Gamepiece getGamepiece() {
        return gamepiece;
    }

    public Location getLocation() {
        return location;
    }

    public String getGamepieceName() {
        return gamepieceName;
    }

    public void setGamepieceName(String gamepieceName) {
        this.gamepieceName = gamepieceName;
    }

    // Test methods

    @Override
    public String toString() {
        return super.toString()+"(player="+player.getPlayerNo()+",location="+location+",gamepiece="+gamepiece+") ";
    }

}
