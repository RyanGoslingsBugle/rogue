package com.rplant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class ScreenState {

    private GameStatus screenStatus;
    private HashMap<String, Integer> gameStatus;
    private BufferedImage board;
    private int currentMenuSelection;
    private BufferedImage treasureImage;
    private BufferedImage menuImage;
    private String menuMessage;
    private boolean isStarted;
    private Difficulty difficulty;

    public ScreenState() {
        try {
            // https://opengameart.org/content/gold-treasure-icons
            URL treasureUrl = this.getClass().getClassLoader().getResource("treasure.png");
            // https://opengameart.org/content/castle-in-the-dark
            URL menuUrl = this.getClass().getClassLoader().getResource("castle.gif");
            if (treasureUrl != null && menuUrl != null) {
                this.treasureImage = ImageIO.read(treasureUrl);
                this.menuImage = ImageIO.read(menuUrl);
            }
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

    public boolean isStarted() {
        return isStarted;
    }

    public BufferedImage getTreasureImage() {
        return treasureImage;
    }

    public BufferedImage getMenuImage() {
        return menuImage;
    }

    public GameStatus getScreenStatus() {
        return screenStatus;
    }

    public String getMenuMessage() {
        return menuMessage;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void update(List<Row> boardRows, int score, int lives, int currentMenuSelection,
                       boolean isStarted, GameStatus gameState
            , String menuMessage, Difficulty difficulty) {
        board = joinBoardImage(boardRows);
        gameStatus = new HashMap<>();
        gameStatus.put("score", score);
        gameStatus.put("lives", lives);
        this.currentMenuSelection = currentMenuSelection;
        this.isStarted = isStarted;
        this.screenStatus = gameState;
        this.menuMessage = menuMessage;
        this.difficulty = difficulty;
    }

    // http://kalanir.blogspot.com/2010/02/how-to-merge-multiple-images-into-one.html
    private BufferedImage joinBoardImage(List<Row> boardRows) {
        int cols = Constants.BOARD_WIDTH;
        int rows = Constants.BOARD_HEIGHT;
        int partWidth = Constants.TILE_WIDTH;
        int partHeight = Constants.TILE_HEIGHT;

        BufferedImage[] boardParts = new BufferedImage[rows * cols];

        int counter = 0;
        for (int y_coord = 0; y_coord < rows; y_coord++) {
            for (int x_coord = 0; x_coord < cols; x_coord++) {
                Tile current = boardRows.get(y_coord).getTiles().get(x_coord);
                BufferedImage temp = current.getTiletype().getImg();
                // https://stackoverflow.com/questions/31185160/how-to-copy-an-image-bufferedimage
                ColorModel model = temp.getColorModel();
                WritableRaster raster = temp.copyData(null);
                BufferedImage clone = new BufferedImage(model, raster, model.isAlphaPremultiplied(), null);
                // draw number of objects if greater than 1
                if (current.getNumObjs() > 0) {
                    Graphics2D g2d = clone.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Courier New", Font.PLAIN, Constants.TILE_WIDTH / 4));
                    g2d.drawString(String.valueOf(current.getNumObjs() + 1), Constants.TILE_WIDTH - 15,
                            Constants.TILE_HEIGHT - 10);
                    g2d.dispose();
                }
                boardParts[counter] = clone;
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
