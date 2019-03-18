package com.rplant;

import java.io.Serializable;

public class Tile implements Serializable {
    private OBJECT_TYPE tiletype;

    public Tile(OBJECT_TYPE tiletype) {
        this.tiletype = tiletype;
    }

    public OBJECT_TYPE getTiletype() {
        return tiletype;
    }
}
