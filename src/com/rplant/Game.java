package com.rplant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {

    private GUI gui = new GUI();
    private GameState gs = GameState.getState();

    public Game() {
        setUpGame();
        setUI();
    }

    private void setUI() {
        gui.updateGUI(gs.getBoard(), gs.getStatus());
        add(gui);
        setResizable(false);
        pack();
        setTitle("Dungeon Rogue");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.addKeyListener(this);
    }

    private void setUpGame() {
        gs.setInGame(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gs.setLastKeyPressed(e.getKeyCode());
        if (gs.isInGame()) {
            gs.update();
            gui.updateGUI(gs.getBoard(), gs.getStatus());
            gui.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }
}