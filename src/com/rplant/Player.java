package com.rplant;

public class Player extends GameObject {
    private static Player player;
    private boolean moved;

    private Player() {
        this.moveBehaviour = new RandomMove();
        this.tile = new Tile(Tile.TileType.PLAYER);
    }

    public static Player getPlayerInstance() {
       if (player == null) {
           player = new Player();
       }

       return player;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setMoved(boolean hasMoved) {
        this.moved = hasMoved;
    }
}
