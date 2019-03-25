package com.rplant;

public enum Difficulty {
    EASY(40, 3, 3),
    NORMAL(60, 2, 1);

    private final int numEnemies;
    private final int numToKill;
    private final int lives;

    Difficulty(int numEnemies, int numToKill, int lives) {
        this.numEnemies = numEnemies;
        this.numToKill = numToKill;
        this.lives = lives;
    }

    public int getNumEnemies() {
        return numEnemies;
    }

    public int getNumToKill() {
        return numToKill;
    }

    public int getLives() {
        return lives;
    }
}
