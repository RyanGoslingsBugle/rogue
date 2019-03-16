package com.rplant;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    // initialization block
    private static GameState state;

    private GameState() {
        clearState();
    }

    public static GameState getState() {
        if (state == null) {
            state = new GameState();
        }

        return state;
    }

    public void clearState() {
        enemies = new ArrayList<>();
        rows = new ArrayList<>();
        newGameStatus();
        player.clearPlayer();
        updateMap();
    }

    // member objects
    private ArrayList<GameObject> enemies;
    private Player player = Player.getPlayerInstance();
    private ArrayList<Row> rows;

    // state vars
    private int score;
    private int lives;
    private boolean gameStarted;
    private GAME_STATE screenStatus;

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public GAME_STATE getScreenStatus() {
        return screenStatus;
    }

    public void setScreenStatus(GAME_STATE screenStatus) {
        this.screenStatus = screenStatus;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void handleKeyPress(int lastKeyPressed) {
        player.handleKeyPress(lastKeyPressed);
    }

    // update methods
    public void update() {
        updateObjects();
        //handleCollisions();
        updateMap();
        //updateStatus();
    }

    private void updateObjects() {
        player.move();
        if (enemies.size() < 2) {
            spawnNewEnemy();
        }
        for (GameObject object:enemies) {
            object.move();
        }

    }

    private void handleCollisions() {
    }

    private void updateStatus() {
    }

    private void newGameStatus() {
        score = 0;
        lives = 3;
    }

    // map methods
    private void updateMap() {
        // set all tiles blank
        setBlankTiles();
        // set player position
        rows.get(player.y_position).getTiles().set(player.x_position, player.tile);
        // update with game object positions
        for (GameObject object:enemies) {
            rows.get(object.y_position).getTiles().set(object.x_position, object.tile);
        }
    }

    private void setBlankTiles() {
        rows.clear();
        for (int row_num = 0; row_num < Constants.BOARD_HEIGHT; row_num++) {
            rows.add(new Row());
        }
    }

    // enemy methods
    private void spawnNewEnemy() {
        Enemy newEnemy = new Warrior();
        enemies.add(newEnemy);
    }

}
