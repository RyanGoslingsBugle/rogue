package com.rplant;

public abstract class GameObject {
    protected int x_position;
    protected int y_position;
    protected Tile tile;
    protected MoveBehaviour moveBehaviour;

    public MoveBehaviour getMoveBehaviour() {
        return moveBehaviour;
    }

    public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public void move() {
        int[] changes = this.moveBehaviour.getMove();
        // wrap around > width/height
        x_position = (x_position + changes[0]) % Constants.BOARD_WIDTH;
        y_position = (y_position + changes[1]) % Constants.BOARD_HEIGHT;
        // wrap around < 0
        x_position = x_position >= 0 ? x_position : Constants.BOARD_WIDTH + x_position;
        y_position = y_position >= 0 ? y_position : Constants.BOARD_HEIGHT + y_position;
    }
}
