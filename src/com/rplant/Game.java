package com.rplant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {

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
    }

    private void setUpGame() {
        // https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
        Timer timer = new Timer(Constants.SPEED, this);
        timer.start();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gs.update();
        gui.updateGUI(gs.getBoard(), gs.getStatus());
        gui.repaint();
    }
}