package ecs.systems;

import de.gurkenlabs.litiengine.input.Input;
import util.input.InputHandler;

public class InputSystem extends System {
  @Override
  public void execute() {
    InputHandler.clearInputs();
    InputHandler.input
        .keySet()
        .forEach(
            (a) -> {
              if (Input.keyboard().isPressed(InputHandler.input.get(a).getKeycode())) {
                InputHandler.input.get(a).setPressed(true);
              }
            });
  }
}
