package com.rplant;

import java.util.ArrayList;

public class Row {
    private ArrayList<Tile> tiles = new ArrayList<>();

    public Row() {
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            tiles.add(new Tile(Tile.TileType.BLANK));
        }
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
