package com.rplant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.IntStream;

public class Game extends JFrame implements KeyListener {

    private GUI gui = new GUI();
    private GameState gs = GameState.getState();
    private int currentSelection = 0;

    public Game() {
        setUpGame();
        setUI();
    }

    private void setUI() {
        add(gui);
        setResizable(false);
        pack();
        setTitle("Dungeon Rogue");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.addKeyListener(this);
    }

    private void setUpGame() {
        gs.clearState();
        gs.setInGame(false);
        gs.setGameStarted(false);
    }

    private void updateGUI() {
        gui.updateGUI(gs.getBoard(), gs.getStatus());
        gui.setCurrentMenuSelection(this.currentSelection);
        gui.repaint();
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_LEFT:
                if (currentSelection > 0 ) {
                    currentSelection--;
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_RIGHT:
                if (currentSelection < Constants.MENU_OPTIONS.length - 1) {
                    currentSelection++;
                }
                break;
        }
    }

    private void selectMenuItem() {
        switch (currentSelection) {
            case 0:
                startNewGame();
                break;
            case 1:
                loadGame();
                break;
            case 2:
                if (gs.isGameStarted()) {
                    saveGame();
                }
                break;
            case 3:
                showHelp();
                break;
        }
    }

    private void startNewGame() {
        System.out.println("Starting new game");
        gs.clearState();
        gs.setInGame(true);
        gs.setGameStarted(true);
        gui.setInGame(true);
        gui.updateGUI(gs.getBoard(), gs.getStatus());
        gui.repaint();
    }

    private void loadGame() {
        //TODO
    }

    private void saveGame() {
        //TODO
    }

    private void showHelp() {
        //TODO
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // flip the menu/game UI if the game has started
        if (keyCode == KeyEvent.VK_ESCAPE && gs.isGameStarted()) {
            gui.setInGame(!gs.isInGame());
            gs.setInGame(!gs.isInGame());
        }
        // handle an Enter press in the menu
        else if (keyCode == KeyEvent.VK_ENTER && !gs.isInGame()) {
            selectMenuItem();
        }
        // If it's any other legal game key
        // https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java
         else if (IntStream.of(Constants.LEGAL_KEYS).anyMatch(x -> x == keyCode)) {
             if (gs.isInGame()) {
                 gs.handleKeyPress(keyCode);
                 gs.update();

             } else {
                 this.handleKeyPress(keyCode);
             }
        }

         updateGUI();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }
}