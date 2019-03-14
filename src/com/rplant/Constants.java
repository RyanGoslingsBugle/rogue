package com.rplant;

import java.awt.event.KeyEvent;

public abstract class Constants {
    public static final int BOARD_WIDTH = 12;
    public static final int BOARD_HEIGHT = 12;
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    public static final int STATUS_HEIGHT = 100;
    public static final int WINDOW_WIDTH = TILE_WIDTH * BOARD_WIDTH;
    public static final int WINDOW_HEIGHT = TILE_HEIGHT * BOARD_HEIGHT + STATUS_HEIGHT;
    public static final int TEXT_SIZE = 24;
    public static final int HEADER_TEXT_SIZE = 36;
    public static final int[] LEGAL_KEYS = new int[] {KeyEvent.VK_NUMPAD1,KeyEvent.VK_NUMPAD2,
            KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8,
            KeyEvent.VK_NUMPAD9, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    public static final String[] MENU_OPTIONS = new String[] {"New Game", "Load Game", "Save Game", "Help"};
}
