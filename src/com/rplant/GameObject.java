package com.rplant;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    protected int x_position;
    protected int y_position;
    protected ObjectType objectType;
    protected MoveBehaviour moveBehaviour;
    protected Tile tile;
    private int playerXPosition;
    private int playerYPosition;

    public void move(int[] playerPosition) {
        this.playerXPosition = playerPosition[0];
        this.playerYPosition = playerPosition[1];
        int[] changes = this.moveBehaviour.getMove(getPlayerPosition(), getPosition());

        // Stop at bounds
        if (0 <= x_position + changes[0] && x_position + changes[0] <= Constants.BOARD_WIDTH - 1) {
            x_position = x_position + changes[0];
        }
        if (0 <= y_position + changes[1] && y_position + changes[1] <= Constants.BOARD_HEIGHT - 1) {
            y_position = y_position + changes[1];
        }
    }

    private int[] getPlayerPosition() {
        return new int[]{ playerXPosition, playerYPosition };
    }

    public int[] getPosition() {
        return new int[]{ x_position, y_position };
    }
}
