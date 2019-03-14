package com.rplant;

public class Player extends GameObject {
    private static Player player;
    private int lastKeyPressed;

    private Player() {
        this.moveBehaviour = new PlayerMove();
        this.tile = new Tile(Tile.TileType.PLAYER);
    }

    public static Player getPlayerInstance() {
       if (player == null) {
           player = new Player();
       }

       return player;
    }

    public void setLastKeyPressed(int lastKeyPressed) {
        this.lastKeyPressed = lastKeyPressed;
        PlayerMove move = (PlayerMove) this.moveBehaviour;
        move.setKeyCode(lastKeyPressed);
    }
}
