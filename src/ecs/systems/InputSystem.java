package ecs.systems;

import de.gurkenlabs.litiengine.input.Input;
import util.InputHandler;

public class InputSystem extends System{
    @Override
    public void execute() {
        InputHandler.clearInputs();
        InputHandler.INPUT.keySet().forEach((a)->{
            if (Input.keyboard().isPressed(InputHandler.INPUT.get(a).getKeycode())) {
                InputHandler.INPUT.get(a).setPressed(true);
            }
        });
    }
}
