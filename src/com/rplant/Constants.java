package com.rplant;

public abstract class Constants {
    public static final int BOARD_WIDTH = 12;
    public static final int BOARD_HEIGHT = 12;
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    public static final int STATUS_HEIGHT = 100;
    public static final int WINDOW_WIDTH = TILE_WIDTH * BOARD_WIDTH;
    public static final int WINDOW_HEIGHT = TILE_HEIGHT * BOARD_HEIGHT + STATUS_HEIGHT;
    public static final int SPEED = 1600;
    public static final int TEXT_SIZE = 24;
    public static final String IMAGE_RESOURCES = System.getProperty("user.dir") + "/img/";
}
