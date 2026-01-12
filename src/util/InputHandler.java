package util;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class InputHandler {

  public static HashMap<String, InputInfo> INPUT = new HashMap<>();

  public static void init() {
    INPUT.put("FORWARD", new InputInfo(KeyEvent.VK_W, false));
  }

  public static void clearInputs() {
    for (InputInfo value : INPUT.values()) {
      value.setPressed(false);
    }
  }
}
