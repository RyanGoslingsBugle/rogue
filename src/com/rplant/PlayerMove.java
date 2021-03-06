package com.rplant;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class PlayerMove implements MoveBehaviour, Serializable {

    private int keyCode;

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    protected int getKeyCode() {
        return keyCode;
    }

    @Override
    public int[] getMove(int[] playerPosition, int[] currentPosition) {
        int x_position_change = 0;
        int y_position_change = 0;
        // https://stackoverflow.com/questions/23876885/keylisteners-with-numpad
        switch (keyCode) {
            case KeyEvent.VK_NUMPAD1:
                x_position_change--;
                y_position_change++;
                break;
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_DOWN:
                y_position_change++;
                break;
            case KeyEvent.VK_NUMPAD3:
                x_position_change++;
                y_position_change++;
                break;
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_LEFT:
                x_position_change--;
                break;
            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_RIGHT:
                x_position_change++;
                break;
            case KeyEvent.VK_NUMPAD7:
                x_position_change--;
                y_position_change--;
                break;
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_UP:
                y_position_change--;
                break;
            case KeyEvent.VK_NUMPAD9:
                x_position_change++;
                y_position_change--;
                break;
        }
        return new int[] { x_position_change, y_position_change};
    }
}
