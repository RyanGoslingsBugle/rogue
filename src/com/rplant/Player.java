package com.rplant;

public class Player extends GameObject {
    private static Player player;

    private Player() {
        this.objectType = OBJECT_TYPE.PLAYER;
        this.moveBehaviour = new PlayerMove();
        this.tile = new Tile(objectType);
    }

    public static Player getPlayerInstance() {
       if (player == null) {
           player = new Player();
       }

       return player;
    }

    public void clearPlayer() {
        this.x_position = Constants.BOARD_WIDTH / 2 - 1;
        this.y_position = Constants.BOARD_HEIGHT / 2 - 1;
    }

    public void handleKeyPress(int lastKeyPressed) {
        PlayerMove move = (PlayerMove) this.moveBehaviour;
        move.setKeyCode(lastKeyPressed);
    }
}
