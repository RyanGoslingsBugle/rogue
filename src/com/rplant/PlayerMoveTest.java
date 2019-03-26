package com.rplant;

import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class PlayerMoveTest {

    private final PlayerMove mb = new PlayerMove();

    @Test
    public void setKeyCode() {
        mb.setKeyCode(KeyEvent.VK_UP);
        assertEquals(KeyEvent.VK_UP, mb.getKeyCode());
    }

    @Test
    public void getMove() {
        mb.setKeyCode(KeyEvent.VK_NUMPAD1);
        int[] directions = mb.getMove(new int[] {0, 0}, new int[] {3, 4});
        assertEquals(-1, directions[0]);
        assertEquals(1, directions[1]);
    }
}