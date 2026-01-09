package util;

import java.awt.event.KeyEvent;

public class InputInfo {

    private int keycode;
    private boolean isPressed;

    public InputInfo(int keycode, boolean isPressed) {
        this.keycode = keycode;
        this.isPressed = isPressed;
    }

    public int getKeycode() {
        return keycode;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public boolean isPressed() {
        return isPressed;
    }
}
