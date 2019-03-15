package com.rplant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenState {

    private GAME_STATE screenStatus;
    private HashMap<String, Integer> gameStatus;
    private BufferedImage board;
    private int currentMenuSelection;
    private BufferedImage treasureImage;
    private BufferedImage menuImage;
    private String menuMessage;

    public ScreenState() {
        try {
            // https://opengameart.org/content/gold-treasure-icons
            this.treasureImage = ImageIO.read(this.getClass().getClassLoader().getResource("treasure.png"));
            // https://opengameart.org/content/castle-in-the-dark
            this.menuImage = ImageIO.read(this.getClass().getClassLoader().getResource("castle.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getGameStatus() {
        return gameStatus;
    }

    public BufferedImage getBoard() {
        return board;
    }

    public int getCurrentMenuSelection() {
        return currentMenuSelection;
    }

    public BufferedImage getTreasureImage() {
        return treasureImage;
    }

    public BufferedImage getMenuImage() {
        return menuImage;
    }

    public GAME_STATE getScreenStatus() {
        return screenStatus;
    }

    public String getMenuMessage() {
        return menuMessage;
    }

    public void update(ArrayList<Row> boardRows, int score, int lives, int currentMenuSelection, GAME_STATE gameState
            , String menuMessage) {
        board = joinBoardImage(boardRows);
        gameStatus = new HashMap<>();
        gameStatus.put("score", score);
        gameStatus.put("lives", lives);
        this.currentMenuSelection = currentMenuSelection;
        this.screenStatus = gameState;
        this.menuMessage = menuMessage;
    }

    // http://kalanir.blogspot.com/2010/02/how-to-merge-multiple-images-into-one.html
    private BufferedImage joinBoardImage(ArrayList<Row> boardRows) {
        int cols = Constants.BOARD_WIDTH;
        int rows = Constants.BOARD_HEIGHT;
        int partWidth = Constants.TILE_WIDTH;
        int partHeight = Constants.TILE_HEIGHT;

        BufferedImage[] boardParts = new BufferedImage[rows * cols];

        int counter = 0;
        for (int y_coord = 0; y_coord < rows; y_coord++) {
            for (int x_coord = 0; x_coord < cols; x_coord++) {
                Tile current = boardRows.get(y_coord).getTiles().get(x_coord);
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
