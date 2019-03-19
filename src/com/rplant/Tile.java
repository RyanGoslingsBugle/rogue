package com.rplant;

import java.io.Serializable;

public class Tile implements Serializable {
    private final ObjectType tiletype;

    public Tile(ObjectType tiletype) {
        this.tiletype = tiletype;
    }

    public ObjectType getTiletype() {
        return tiletype;
    }
}
