package com.rplant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameState implements Serializable {

    // initialization block
    private static GameState state;

    private GameState() {
        difficulty = Difficulty.NORMAL;
        clearState();
    }

    public static GameState getState() {
        if (state == null) {
            state = new GameState();
        }

        return state;
    }

    public void clearState() {
        enemies = new LinkedList<>();
        rows = new ArrayList<>();
        player.clearPlayer();
        newGameStatus();
        ObjectType.init();
        updateMap();
    }

    // member objects
    private List<GameObject> enemies;
    private final Player player = Player.getPlayerInstance();
    private List<Row> rows;

    // state vars
    private int score;
    private int lives;
    private boolean gameStarted;
    private GameStatus screenStatus;
    private Difficulty difficulty;

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void changeDifficulty() {
        if (difficulty == Difficulty.NORMAL) {
            difficulty = Difficulty.EASY;
            lives += Difficulty.EASY.getLives() - Difficulty.NORMAL.getLives();
        } else {
            difficulty = Difficulty.NORMAL;
            lives = Math.max(1, lives - (Difficulty.EASY.getLives() - Difficulty.NORMAL.getLives()));
        }
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

    public List<Row> getRows() {
        return rows;
    }

    public void handleKeyPress(int lastKeyPressed) {
        player.handleKeyPress(lastKeyPressed);
    }

    // update methods
    public void update() {
        updateObjects();
        updateMap();
    }

    private void updateObjects() {

        // move player
        player.move(player.getPosition());
        checkCollisions();

        // move enemies
        for (GameObject object : enemies) {
            object.move(player.getPosition());
        }

        checkCollisions();

        // spawn new enemy if less than desired amount
        if (enemies.size() < difficulty.getNumEnemies()) {
            if (Math.random() < (1/3.0d)) {
                spawnNewEnemy();
            }
        }
    }

    private void checkCollisions() {
        int collisionCount = 0;
        ArrayList<GameObject> collided = new ArrayList<>();

        for (GameObject object : enemies) {
            if (Arrays.equals(player.getPosition(), object.getPosition())) { // test if on the same tile
                collided.add(object); // add to new list if collision has occurred
                collisionCount++;
            }
        }

        if (collisionCount < this.difficulty.getNumToKill()) {
            for (GameObject object : collided) { // iterate collided list and remove
                killEnemy((Enemy) object);
                score += ((Enemy) object).scoreVal;
            }
        } else {
            if (lives > 1) { // remove life
                SoundEffect.HURT.play();
                lives --;
            } else { // if no lives left, end game
                gameOver();
            }
        }
    }

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
        // set spawn tile
        rows.get(0).getTiles().set(0, new Tile(ObjectType.SPAWN));
        // set player position
        rows.get(player.y_position).getTiles().set(player.x_position, player.tile);
        // update with game object positions
        for (GameObject object:enemies) {
            // count other enemies on the same tile
            int overlap = 0;
            for (GameObject otherObject:enemies) {
                if (!object.equals(otherObject )) {
                    if (Arrays.equals(object.getPosition(), otherObject.getPosition())) {
                        overlap++;
                    }
                }
            }
            object.tile.setNumObjs(overlap);
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
        // https://stackoverflow.com/questions/6737283/weighted-randomness-in-java
        List<Enemy> allEnemies = new ArrayList<>();
        allEnemies.add(new Goblin());
        allEnemies.add(new Warrior());
        allEnemies.add(new Imp());
        double totalProb = 0.0d;
        for (Enemy currentEnemy : allEnemies) {
            totalProb += currentEnemy.getProbability();
        }
        int randomIndex = -1;
        double randomPick = Math.random() * totalProb;
        for (int i = 0; i < allEnemies.size(); ++i)
        {
            Enemy currentEnemy = allEnemies.get(i);
            randomPick -= currentEnemy.getProbability();
            if (randomPick <= 0.0d)
            {
                randomIndex = i;
                break;
            }
        }

        Enemy newEnemy = allEnemies.get(randomIndex);

        enemies.add(newEnemy);
    }

    private void killEnemy(Enemy enemy) {
        enemy.kill();
        enemies.remove(enemy);
    }

}
