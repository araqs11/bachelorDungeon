package ecs.components;

import ecs.core.Component;
import game.GameLoop;
import java.awt.*;
import util.statemaschine.StateMachine;

public class DrawComponent extends Component {

  private StateMachine stateMachine;
  private int stateDuration = 4;
  private int current_duration = 0;

  public DrawComponent(StateMachine stateMachine, int stateDuration) {
    this.stateMachine = stateMachine;
    this.stateDuration = stateDuration;
  }

  public DrawComponent(StateMachine stateMachine) {
    this(stateMachine, 1);
  }

  public Image getScaledImage() throws Exception {
    return stateMachine
        .getCurrentState()
        .getResource()
        .getScaledInstance(
            GameLoop.RENDERSCALE * GameLoop.ZOOM, GameLoop.RENDERSCALE * GameLoop.ZOOM, 1);
  }

  public void nextImage() {
    if (current_duration >= stateDuration) {
      stateMachine.nextState();
      current_duration = 0;
    } else {
      current_duration++;
    }
  }
}
