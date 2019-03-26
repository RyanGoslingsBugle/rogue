package com.rplant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HuntMoveTest {

    private final HuntMove mb = new HuntMove();

    @Test
    public void getMove() {
        int[] directions = mb.getMove(new int[] {1, 4}, new int[] {1, 1});
        assertEquals(0, directions[0]);
        assertEquals(1, directions[1]);
    }
}