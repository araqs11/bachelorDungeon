package util.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class InputHandler {

  public static HashMap<InputName, InputInfo> input = new HashMap<>();

  public static void init() {
    input.put(InputName.FORWARD, new InputInfo(KeyEvent.VK_W, false));
    input.put(InputName.BACKWARD, new InputInfo(KeyEvent.VK_S, false));
    input.put(InputName.LEFT, new InputInfo(KeyEvent.VK_A, false));
    input.put(InputName.RIGHT, new InputInfo(KeyEvent.VK_D, false));
    input.put(InputName.TESTING, new InputInfo(KeyEvent.VK_K, false));
    input.put(InputName.ZOOM_OUT, new InputInfo(KeyEvent.VK_MINUS, false, 500));
    input.put(InputName.ZOOM_IN, new InputInfo(KeyEvent.VK_PLUS, false, 500));
    input.put(InputName.NEXT_LEVEL, new InputInfo(KeyEvent.VK_N, false, 1000));
    input.put(InputName.USE_SKILL, new InputInfo(MouseEvent.BUTTON1, false, 200));
  }

  public static boolean isPressed(InputName name) {
    return input.get(name).isPressed();
  }

  public static HashMap<InputName, InputInfo> getInput() {
    return input;
  }

  public static int getKeyCode(InputName name) {
    return input.get(name).getKeycode();
  }

  public static void setPressed(InputName name) {
    input.get(name).setPressed(true);
  }

  public static void clearInputs() {
    for (InputInfo value : input.values()) {
      value.clearPressed();
    }
  }
}
