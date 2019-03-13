package com.rplant;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GUI extends JPanel {

    private BufferedImage boardImage;
    private HashMap<String, Integer> status;

    public GUI() {
        setUpBoard();
    }

    public void updateGUI(BufferedImage boardImage, HashMap<String, Integer> status) {
        this.boardImage = boardImage;
        this.status = status;
    }

    // http://zetcode.com/tutorials/javagamestutorial/basics/
    private void setUpBoard() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
    }

    private void drawBoard(Graphics g) {
        g.drawImage(boardImage, 0, 0, null);
    }

    private void drawStatus(Graphics g) {

        int max_x = Constants.WINDOW_WIDTH;
        int min_y = Constants.WINDOW_HEIGHT - Constants.STATUS_HEIGHT;

        drawHeart(g, max_x / 5, min_y + (Constants.STATUS_HEIGHT / 3), max_x / 25, Constants.STATUS_HEIGHT / 3);

    }

    // https://stackoverflow.com/questions/33402242/how-to-draw-heart-using-java-awt-libaray
    private void drawHeart(Graphics g, int x, int y, int width, int height) {

        g.setColor(Color.RED);

        int[] triangleX = {
                x - 2*width/18,
                x + width + 2*width/18,
                (x - 2*width/18 + x + width + 2*width/18)/2};
        int[] triangleY = {
                y + height - 2*height/3,
                y + height - 2*height/3,
                y + height };
        g.fillOval(
                x - width/12,
                y,
                width/2 + width/6,
                height/2);
        g.fillOval(
                x + width/2 - width/12,
                y,
                width/2 + width/6,
                height/2);
        g.fillPolygon(triangleX, triangleY, triangleX.length);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, Constants.TEXT_SIZE));
        g.drawString(": " + status.get("lives"), x + width * 2, y + height / 2 + height / 4);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawStatus(g);
    }
}
