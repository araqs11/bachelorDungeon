package util;

public class InputInfo {

  private int keycode;
  private boolean isPressed;
  private int delay;
  private long timeHolder;

  public InputInfo(int keycode, boolean isPressed, int delay) {
    this.keycode = keycode;
    this.isPressed = isPressed;
    this.delay = delay;
    this.timeHolder = System.currentTimeMillis();
  }

  public InputInfo(int keycode, boolean isPressed) {
    this(keycode, isPressed, 0);
  }

  public int getKeycode() {
    return keycode;
  }

  public void setPressed(boolean pressed) {
    if (timeHolder + delay <= System.currentTimeMillis()) {
      isPressed = pressed;
      timeHolder = System.currentTimeMillis();
    }
  }

  public void clearPressed() {
    isPressed = false;
  }

  public boolean isPressed() {
    return isPressed;
  }
}
