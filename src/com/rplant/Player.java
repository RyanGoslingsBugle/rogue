package com.rplant;

public class Player extends GameObject {
    private static Player player;

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

    public void clearPlayer() {
        this.x_position = 0;
        this.y_position = 0;
    }

    public void handleKeyPress(int lastKeyPressed) {
        PlayerMove move = (PlayerMove) this.moveBehaviour;
        move.setKeyCode(lastKeyPressed);
    }
}
