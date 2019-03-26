package com.rplant;

import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private final Player player = Player.getPlayerInstance();

    @Test
    public void handleKeyPress() {
        int currentX = player.x_position;
        int currentY = player.y_position;
        player.handleKeyPress(KeyEvent.VK_UP);
        player.move(new int[] {0, 0});
        int newX = player.x_position;
        int newY = player.y_position;
        currentY--;
        assertEquals(currentX, newX);
        assertEquals(currentY, newY);
    }

    @Test
    public void clearPlayer() {
        player.handleKeyPress(KeyEvent.VK_UP);
        player.move(new int[] {0, 0});
        player.handleKeyPress(KeyEvent.VK_UP);
        player.move(new int[] {0, 0});
        player.clearPlayer();
        assertEquals(Constants.BOARD_WIDTH / 2 - 1, player.x_position);
        assertEquals(Constants.BOARD_HEIGHT / 2 - 1, player.y_position);
    }
}