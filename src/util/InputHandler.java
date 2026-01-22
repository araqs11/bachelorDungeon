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
    INPUT.put("ZOOM_OUT", new InputInfo(KeyEvent.VK_MINUS, false, 500));
    INPUT.put("ZOOM_IN", new InputInfo(KeyEvent.VK_PLUS, false, 500));
    INPUT.put("NEXT_LEVEL", new InputInfo(KeyEvent.VK_N, false, 1000));
  }

  public static void clearInputs() {
    for (InputInfo value : INPUT.values()) {
      value.clearPressed();
    }
  }
}
