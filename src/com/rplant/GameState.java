package com.rplant;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState implements Serializable {

    // initialization block
    private static GameState state;

    private GameState() {
        gameObjects = new ArrayList<>();
        rows = new ArrayList<>();
        status = new HashMap<>();
        newGameStatus();
        gameObjects.add(player);
        updateMap();
    }

    public static GameState getState() {
        if (state == null) {
            state = new GameState();
        }

        return state;
    }

    // member objects
    private ArrayList<GameObject> gameObjects;
    private Player player = Player.getPlayerInstance();
    private ArrayList<Row> rows;
    private HashMap<String, Integer> status;

    // state vars
    private BufferedImage board;
    private int score;
    private int lives;
    private boolean inGame;

    public BufferedImage getBoard() {
        return board;
    }

    public HashMap<String, Integer> getStatus() {
        return status;
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
    }

    private void handleCollisions() {
    }

    private void updateStatus() {
        if (inGame) {

        }
    }

    private void newGameStatus() {
        inGame = true;
        score = 0;
        lives = 3;
        status.put("score", score);
        status.put("lives", lives);
    }

    // map methods
    private void updateMap() {
        // set all tiles blank
        setBlankTiles();
        // update with game object positions
        for (GameObject object:gameObjects) {
            rows.get(object.y_position).getTiles().set(object.x_position, object.tile);
        }
        // join and set board image
        board = joinBoardImage();
    }

    private void setBlankTiles() {
        rows.clear();
        for (int row_num = 0; row_num < Constants.BOARD_HEIGHT; row_num++) {
            rows.add(new Row());
        }
    }

    // http://kalanir.blogspot.com/2010/02/how-to-merge-multiple-images-into-one.html
    private BufferedImage joinBoardImage() {
        int cols = Constants.BOARD_WIDTH;
        int rows = Constants.BOARD_HEIGHT;
        int partWidth = Constants.TILE_WIDTH;
        int partHeight = Constants.TILE_HEIGHT;

        BufferedImage[] boardParts = new BufferedImage[rows * cols];

        int counter = 0;
        for (int y_coord = 0; y_coord < rows; y_coord++) {
            for (int x_coord = 0; x_coord < cols; x_coord++) {
                Tile current = this.rows.get(y_coord).getTiles().get(x_coord);
                boardParts[counter] = current.loadImage();
                counter++;
            }
        }

        BufferedImage finalBoard = new BufferedImage(partWidth * rows,
                partHeight * cols, boardParts[0].getType());

        counter = 0;
        for (int tile_num = 0; tile_num < rows * cols; tile_num++) {
            finalBoard.createGraphics().drawImage(boardParts[counter], partWidth * (counter % cols),
                    partHeight * (counter / rows), null);
            counter++;
        }

        return finalBoard;
    }

}
