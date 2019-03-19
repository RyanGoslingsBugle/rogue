package com.rplant;

public enum Difficulty {
    EASY(2, 3, 3),
    NORMAL(3, 2, 1);

    private int numEnemies;
    private int numToKill;
    private int lives;

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
