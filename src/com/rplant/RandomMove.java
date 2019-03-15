package com.rplant;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMove implements MoveBehaviour, Serializable {
    @Override
    public int[] getMove() {
        int x_position_change = ThreadLocalRandom.current().nextInt(-1,2);
        int y_position_change = ThreadLocalRandom.current().nextInt(-1,2);

        return new int[] { x_position_change, y_position_change};
    }
}
