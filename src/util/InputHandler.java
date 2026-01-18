package util;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class InputHandler {

  public static HashMap<String, InputInfo> INPUT = new HashMap<>();

  public static void init() {
    INPUT.put("FORWARD", new InputInfo(KeyEvent.VK_W, false));
    INPUT.put("BACKWARD", new InputInfo(KeyEvent.VK_S, false));
    INPUT.put("LEFT", new InputInfo(KeyEvent.VK_A, false));
    INPUT.put("RIGHT", new InputInfo(KeyEvent.VK_D, false));
    INPUT.put("TESTING", new InputInfo(KeyEvent.VK_K, false));
  }

  public static void clearInputs() {
    for (InputInfo value : INPUT.values()) {
      value.setPressed(false);
    }
  }
}
