package com.rplant;

public class Warrior extends Enemy {
    public Warrior() {
        this.objectType = ObjectType.WARRIOR;
        this.moveBehaviour = new RandomMove();
        this.tile = new Tile(objectType);
    }
}
