package com.rplant;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    protected int x_position;
    protected int y_position;
    protected OBJECT_TYPE objectType;
    protected MoveBehaviour moveBehaviour;
    protected Tile tile;

    public MoveBehaviour getMoveBehaviour() {
        return moveBehaviour;
    }

    public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public void move() {
        int[] changes = this.moveBehaviour.getMove();
//        // wrap around > width/height
//        x_position = (x_position + changes[0]) % Constants.BOARD_WIDTH;
//        y_position = (y_position + changes[1]) % Constants.BOARD_HEIGHT;
//        // wrap around < 0
//        x_position = x_position >= 0 ? x_position : Constants.BOARD_WIDTH + x_position;
//        y_position = y_position >= 0 ? y_position : Constants.BOARD_HEIGHT + y_position;
        if (0 <= x_position + changes[0] && x_position + changes[0] <= Constants.BOARD_WIDTH - 1) {
            x_position = x_position + changes[0];
        }
        if (0 <= y_position + changes[1] && y_position + changes[1] <= Constants.BOARD_HEIGHT - 1) {
            y_position = y_position + changes[1];
        }
    }
}
