package com.rplant;

import java.awt.event.KeyEvent;

public class PlayerMove implements MoveBehaviour {

    private int keyCode;

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public int[] getMove() {
        int x_position_change = 0;
        int y_position_change = 0;
        // https://stackoverflow.com/questions/23876885/keylisteners-with-numpad
        switch (keyCode) {
            case KeyEvent.VK_NUMPAD1:
                x_position_change--;
                y_position_change++;
                break;
            case KeyEvent.VK_NUMPAD2:
                y_position_change++;
                break;
            case KeyEvent.VK_NUMPAD3:
                x_position_change++;
                y_position_change++;
                break;
            case KeyEvent.VK_NUMPAD4:
                x_position_change--;
                break;
            case KeyEvent.VK_NUMPAD6:
                x_position_change++;
                break;
            case KeyEvent.VK_NUMPAD7:
                x_position_change--;
                y_position_change--;
                break;
            case KeyEvent.VK_NUMPAD8:
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
