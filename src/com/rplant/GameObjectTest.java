package com.rplant;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class GameObjectTest {
    private final GameObject go = new Warrior();

    @Test
    public void move() {
        int[] currentPos =  go.getPosition();
        go.move(new int[]{0,0});
        assertNotEquals(currentPos, go.getPosition());
    }

    @Test
    public void getPosition() {
        assertNotNull(go.getPosition());
    }
}