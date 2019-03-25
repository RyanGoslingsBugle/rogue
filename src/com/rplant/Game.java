package com.rplant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.stream.IntStream;

public class Game extends JFrame implements KeyListener, ActionListener {

    private final GUI gui = new GUI();
    private final SoundManager snd = new SoundManager();
    private GameState gs = GameState.getState();
    private final ScreenState state = new ScreenState();
    private int currentSelection = 0;
    private String menuMessage;

    private Game() {
        setUpGame();
        setUI();
    }

    private void setUI() {
        add(gui);
        setResizable(false);
        pack();
        setTitle("Dungeon Rogue");
        setLocationRelativeTo(null);
        gui.addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Timer timer = new Timer(Constants.RENDER_DELAY, this);
        timer.start();
    }

    private void setUpGame() {
        gs.clearState();
        gs.setScreenStatus(GameStatus.MENU);
        gs.setGameStarted(false);
    }

    private void updateGUI() {
        snd.update(gs.getScreenStatus());
        state.update(gs.getRows(), gs.getScore(), gs.getLives(), this.currentSelection, gs.isGameStarted(),
                gs.getScreenStatus(), this.menuMessage, gs.getDifficulty());
        gui.updateGUI(state);
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
        SoundEffect.SELECT.play();
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
            case 4:
                changeDifficulty();
                break;
        }
    }

    private void startNewGame() {
        System.out.println("Starting new game...");
        gs.clearState();
        gs.setScreenStatus(GameStatus.GAME);
        gs.setGameStarted(true);
    }

    private void loadGame() {
        System.out.println("Loading game...");
        ObjectInputStream in;
        String message = "";
        try {
            in = new ObjectInputStream(new FileInputStream("rogue.sav"));
            gs = (GameState) in.readObject();
            gs.setScreenStatus(GameStatus.GAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            message = "No save file";
        } catch (IOException e) {
            message = "Couldn't read save";
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            message = "Failed to load";
            e.printStackTrace();
        }

        this.menuMessage = message;
    }

    private void saveGame() {
        System.out.println("Saving game....");
        ObjectOutputStream out;
        String message;
        try {
            out = new ObjectOutputStream(new FileOutputStream("rogue.sav"));
            out.writeObject(gs);
            out.close();
            message = "Saved";
        } catch (IOException e) {
            e.printStackTrace();
            message = "Save failed";
        }

        this.menuMessage = message;
    }

    private void showHelp() {
        gs.setScreenStatus(GameStatus.HELP);
    }

    private void changeDifficulty() {
        gs.changeDifficulty();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gui.isFocusOwner()) {
            this.menuMessage = "";
            int keyCode = e.getKeyCode();
            GameStatus state = gs.getScreenStatus();
            // flip the menu/game UI if the game has started
            if (keyCode == KeyEvent.VK_ESCAPE && gs.isGameStarted()) {
                if (state != GameStatus.MENU) {
                    gs.setScreenStatus(GameStatus.MENU);
                } else {
                    gs.setScreenStatus(GameStatus.GAME);
                }
            } else if (state == GameStatus.HELP || state == GameStatus.GAME_OVER) {
                gs.setScreenStatus(GameStatus.MENU);
            }
            // secret player kill key
            else if (keyCode == KeyEvent.VK_K && gs.isGameStarted()) {
                gs.gameOver();
            }
            // handle an Enter press in the menus
            else if (keyCode == KeyEvent.VK_ENTER && state == GameStatus.MENU) {
                selectMenuItem();
            }
            // If it's any other legal game key
            // https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java
            else if (IntStream.of(Constants.LEGAL_KEYS).anyMatch(x -> x == keyCode)) {
                if (state == GameStatus.GAME) {
                    gs.handleKeyPress(keyCode);
                    gs.update();
                } else if (state == GameStatus.MENU) {
                    this.handleKeyPress(keyCode);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGUI();
        gui.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame game = new Game();
            game.setVisible(true);
        });
    }
}