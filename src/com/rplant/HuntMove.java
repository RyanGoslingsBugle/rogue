package com.rplant;

import java.io.Serializable;

public class HuntMove implements MoveBehaviour, Serializable {

    @Override
    public int[] getMove(int[] playerPosition, int[] currentPosition) {

        int x_position_change = Integer.compare(playerPosition[0], currentPosition[0]);
        int y_position_change = Integer.compare(playerPosition[1], currentPosition[1]);

        return new int[] { x_position_change, y_position_change};
    }
}
