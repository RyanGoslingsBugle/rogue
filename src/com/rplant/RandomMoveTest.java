package com.rplant;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RandomMoveTest {

    private final RandomMove mb = new RandomMove();

    @Test
    public void getMove() {
        int[] directions = mb.getMove(new int[] {0,0}, new int[] {3, 4});
        assertTrue(directions[0] < 2);
        assertTrue(directions[0] > -2);
        assertTrue(directions[1] < 2);
        assertTrue(directions[1] > -2);
    }
}