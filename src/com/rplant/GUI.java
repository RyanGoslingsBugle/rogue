package com.rplant;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {

    private ScreenState screenState;

    public GUI() {
        setUpBoard();
    }

    public void updateGUI(ScreenState state) {
        this.screenState = state;
    }

    // http://zetcode.com/tutorials/javagamestutorial/basics/
    private void setUpBoard() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
    }

    private void drawBoard(Graphics g) {
        g.drawImage(screenState.getBoard(), 0, 0, null);
    }

    private void drawStatus(Graphics g) {

        int max_x = Constants.WINDOW_WIDTH;
        int min_y = Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT;

        drawHeart(g, max_x / 5, min_y + (Constants.STATUS_HEIGHT / 3), max_x / 15,
                Constants.STATUS_HEIGHT / 3, min_y + Constants.STATUS_HEIGHT / 2);
        drawTreasure(g, max_x - max_x / 5 * 2, min_y + (Constants.STATUS_HEIGHT / 4), max_x / 10,
                Constants.STATUS_HEIGHT / 2, min_y + Constants.STATUS_HEIGHT / 2);

    }

    private void drawTreasure(Graphics g, int x, int y, int width, int height, int textHeight) {

        g.drawImage(screenState.getTreasureImage(), x, y, width, height, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, Constants.TEXT_SIZE));
        g.drawString(": " + screenState.getGameStatus().get("score"), x + width * 2, textHeight);
    }

    // https://stackoverflow.com/questions/33402242/how-to-draw-heart-using-java-awt-libaray
    private void drawHeart(Graphics g, int x, int y, int width, int height, int textHeight) {

        g.setColor(Color.RED);

        int[] triangleX = {x - 2*width/18, x + width + 2*width/18, (x - 2*width/18 + x + width + 2*width/18)/2};
        int[] triangleY = {y + height - 2*height/3, y + height - 2*height/3, y + height };
        g.fillOval(x - width/12, y,width/2 + width/6,height/2);
        g.fillOval(x + width/2 - width/12, y,width/2 + width/6,height/2);
        g.fillPolygon(triangleX, triangleY, triangleX.length);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, Constants.TEXT_SIZE));
        g.drawString(": " + screenState.getGameStatus().get("lives"), x + width * 2, textHeight);
    }

    private void drawMenu(Graphics g) {
        g.drawImage(screenState.getMenuImage(),0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT,
                null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, Constants.HEADER_TEXT_SIZE));
        String title = "Dungeon Rogue";
        // https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
        FontMetrics lMetrics = g.getFontMetrics(g.getFont());
        g.drawString(title, (Constants.WINDOW_WIDTH - lMetrics.stringWidth(title)) / 2,
                Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT * 2);

        int i = 0;
        for (String option: Constants.MENU_OPTIONS) {
            // Gray out Save option if game hasn't started
            if (!screenState.isStarted() && option.equals("Save Game")) {
                g.setColor(Color.GRAY);
                g.setFont(new Font("Courier New", Font.PLAIN, Constants.TEXT_SIZE));
            } // Display current choice bold
            else if (screenState.getCurrentMenuSelection() == i) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Courier New", Font.BOLD, Constants.TEXT_SIZE));
            } // Display other choices plain
            else {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Courier New", Font.PLAIN, Constants.TEXT_SIZE));
            }
            FontMetrics sMetrics = g.getFontMetrics(g.getFont());
            int box =
                    Constants.WINDOW_WIDTH / (Constants.MENU_OPTIONS.length + 1) * (i + 1) - sMetrics.stringWidth(option) / 4;
            g.drawString(option, box,
                    Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT / 2);
            i++;
        }

        if (screenState.getMenuMessage() != null && !screenState.getMenuMessage().isEmpty()) {
            String msg = screenState.getMenuMessage();
            g.setFont(new Font("Courier New", Font.PLAIN, Constants.TEXT_SIZE));
            FontMetrics tMetrics = g.getFontMetrics(g.getFont());
            g.drawString(msg, (Constants.WINDOW_WIDTH - tMetrics.stringWidth(msg)) / 2, Constants.WINDOW_HEIGHT / 2);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (screenState != null) {
            switch (screenState.getScreenStatus()) {
                case GAME:
                    drawBoard(g);
                    drawStatus(g);
                    break;
                case MENU:
                    drawMenu(g);
                    break;
                case HELP:
                    break;
                case GAME_OVER:
                    break;
            }
        }
    }
}
