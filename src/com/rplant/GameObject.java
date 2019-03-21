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

        int newXPosition = this.x_position + changes[0];
        int newYPosition = this.y_position + changes[1];

        // player can't move onto spawn point
        if (this.objectType == ObjectType.PLAYER) {
            if (newXPosition == 0 && newYPosition == 0) {
                newXPosition = this.x_position;
                newYPosition = this.y_position;
            }
        }
        // Stop at bounds
        if (0 > newXPosition || newXPosition > Constants.BOARD_WIDTH - 1) {
            newXPosition = this.x_position;
        }
        if (0 > newYPosition || newYPosition > Constants.BOARD_HEIGHT - 1) {

            newYPosition = this.y_position;
        }

        this.x_position = newXPosition;
        this.y_position = newYPosition;

    }

    private int[] getPlayerPosition() {
        return new int[]{ playerXPosition, playerYPosition };
    }

    public int[] getPosition() {
        return new int[]{ x_position, y_position };
    }
}
