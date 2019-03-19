package com.rplant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
        player.clearPlayer();
        difficulty = Difficulty.NORMAL;
        newGameStatus();
        ObjectType.init();
        updateMap();
    }

    // member objects
    private ArrayList<GameObject> enemies;
    private final Player player = Player.getPlayerInstance();
    private ArrayList<Row> rows;

    // state vars
    private int score;
    private int lives;
    private boolean gameStarted;
    private GameStatus screenStatus;
    private Difficulty difficulty;

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public GameStatus getScreenStatus() {
        return screenStatus;
    }

    public void setScreenStatus(GameStatus screenStatus) {
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
        updateMap();
        //updateStatus();
    }

    private void updateObjects() {
        player.move(player.getPosition());
        if (enemies.size() < difficulty.getNumEnemies()) {
            spawnNewEnemy();
        }
        for (GameObject object:enemies) {
            object.move(player.getPosition());
        }
        checkCollisions();
    }

    private void checkCollisions() {
        int collisionCount = 0;
        for (Iterator<GameObject> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
            GameObject object = enemyIterator.next();
            if (Arrays.equals(player.getPosition(), object.getPosition())) {
                collisionCount++;
                System.out.println("collision: " + collisionCount);
                if (collisionCount < this.difficulty.getNumToKill()) {
                    System.out.println("Killing enemy");
                    // https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
                    killEnemy((Enemy)object, enemyIterator);
                } else {
                    if (lives > 0) {
                        SoundEffect.HURT.play();
                        lives --;
                    } else {
                        gameOver();
                    }
                }
            }
        }
    }

//
//    private void updateStatus() {
//    }

    private void newGameStatus() {
        score = 0;
        lives = difficulty.getLives();
    }

    public void gameOver() {
        setScreenStatus(GameStatus.GAME_OVER);
        SoundEffect.LOSE.play();
        gameStarted = false;
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

    private void killEnemy(Enemy enemy, Iterator<GameObject> iterator) {
        enemy.kill();
        iterator.remove();
    }

}
