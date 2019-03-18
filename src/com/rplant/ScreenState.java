package com.rplant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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
    private HashMap<OBJECT_TYPE, BufferedImage> tileImages;
    private boolean isStarted;

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

        tileImages = new HashMap<>();

        for (OBJECT_TYPE obj: OBJECT_TYPE.values()) {
            BufferedImage img = null;
            try {
                URL imgUrl = this.getClass().getClassLoader().getResource(obj.toString().toLowerCase() + ".png");
                if (imgUrl != null) {
                    img = ImageIO.read(imgUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            tileImages.put(obj, img);
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

    public GAME_STATE getScreenStatus() {
        return screenStatus;
    }

    public String getMenuMessage() {
        return menuMessage;
    }

    public void update(ArrayList<Row> boardRows, int score, int lives, int currentMenuSelection,
                       boolean isStarted, GAME_STATE gameState
            , String menuMessage) {
        board = joinBoardImage(boardRows);
        gameStatus = new HashMap<>();
        gameStatus.put("score", score);
        gameStatus.put("lives", lives);
        this.currentMenuSelection = currentMenuSelection;
        this.isStarted = isStarted;
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
                boardParts[counter] = tileImages.get(current.getTiletype());
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
