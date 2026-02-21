package ecs.systems;

import de.gurkenlabs.litiengine.input.Input;
import ecs.core.System;
import util.input.InputHandler;

import java.awt.event.MouseEvent;

public class InputSystem extends System {
  @Override
  public void execute() {
    InputHandler.clearInputs();
    InputHandler.getInput()
        .keySet()
        .forEach(
            (inputName) -> {
              if (Input.keyboard().isPressed(InputHandler.getKeyCode(inputName))) {
                InputHandler.setPressed(inputName);
              }
              if (Input.mouse().isLeftButtonPressed() && InputHandler.getKeyCode(inputName)== MouseEvent.BUTTON1) {
                InputHandler.setPressed(inputName);
              }
            });
  }
}
