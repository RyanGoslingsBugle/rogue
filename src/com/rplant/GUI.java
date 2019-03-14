package com.rplant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class GUI extends JPanel {

    private BufferedImage treasureImage;
    private BufferedImage menuImage;
    private BufferedImage boardImage;
    private HashMap<String, Integer> status;
    private Boolean inGame;
    private int currentMenuSelection;

    public GUI() {
        try {
            // https://opengameart.org/content/gold-treasure-icons
            this.treasureImage = ImageIO.read(this.getClass().getClassLoader().getResource("treasure.png"));
            // https://opengameart.org/content/castle-in-the-dark
            this.menuImage = ImageIO.read(this.getClass().getClassLoader().getResource("castle.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setUpBoard();
    }

    public void updateGUI(BufferedImage boardImage, HashMap<String, Integer> status) {
        this.boardImage = boardImage;
        this.status = status;
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }

    public void setCurrentMenuSelection(int currentMenuSelection) {
        this.currentMenuSelection = currentMenuSelection;
    }

    // http://zetcode.com/tutorials/javagamestutorial/basics/
    private void setUpBoard() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        inGame = false;
    }

    private void drawBoard(Graphics g) {
        g.drawImage(boardImage, 0, 0, null);
    }

    private void drawStatus(Graphics g) {

        int max_x = Constants.WINDOW_WIDTH;
        int min_y = Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT;

        drawHeart(g, max_x / 5, min_y + (Constants.STATUS_HEIGHT / 3), max_x / 25, Constants.STATUS_HEIGHT / 3);
        drawTreasure(g, max_x - max_x / 5 * 2, min_y + (Constants.STATUS_HEIGHT / 4), max_x / 15,
                Constants.STATUS_HEIGHT / 2);

    }

    private void drawTreasure(Graphics g, int x, int y, int width, int height) {

        g.drawImage(treasureImage, x, y, width, height, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, Constants.TEXT_SIZE));
        g.drawString(": " + status.get("score"), x + width * 2, y + height / 2 + height / 5);
    }

    // https://stackoverflow.com/questions/33402242/how-to-draw-heart-using-java-awt-libaray
    private void drawHeart(Graphics g, int x, int y, int width, int height) {

        g.setColor(Color.RED);

        int[] triangleX = {x - 2*width/18, x + width + 2*width/18, (x - 2*width/18 + x + width + 2*width/18)/2};
        int[] triangleY = {y + height - 2*height/3, y + height - 2*height/3, y + height };
        g.fillOval(x - width/12, y,width/2 + width/6,height/2);
        g.fillOval(x + width/2 - width/12, y,width/2 + width/6,height/2);
        g.fillPolygon(triangleX, triangleY, triangleX.length);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, Constants.TEXT_SIZE));
        g.drawString(": " + status.get("lives"), x + width * 2, y + height / 2 + height / 4);
    }

    private void drawMenu(Graphics g) {
        g.drawImage(menuImage,0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, Constants.HEADER_TEXT_SIZE));
        String title = "Dungeon Rogue";
        // https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(title, (Constants.WINDOW_WIDTH - metrics.stringWidth(title)) / 2,
                Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT * 2);

        int i = 0;
        for (String option: Constants.MENU_OPTIONS) {
            if (currentMenuSelection == i) {
                g.setFont(new Font("Courier New", Font.BOLD, Constants.TEXT_SIZE));
            } else {
                g.setFont(new Font("Courier New", Font.PLAIN, Constants.TEXT_SIZE));
            }
            int box =
                    Constants.WINDOW_WIDTH / (Constants.MENU_OPTIONS.length + 1) * (i + 1) - metrics.stringWidth(option) / 4;
            g.drawString(option, box,
                    Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT / 2);
            i++;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            drawBoard(g);
            drawStatus(g);
        } else {
            drawMenu(g);
        }
    }
}
