package util.statemaschine;

import java.util.HashMap;
import java.util.HashSet;

public class StateMachine {

  private HashSet<State> states;
  private HashMap<String, String> transitions;
  private String currentState = "";

  public StateMachine() {
    states = new HashSet<>();
    transitions = new HashMap<>();
  }

  public void addState(State state) {
    states.add(state);
    if (currentState.isEmpty()) {
      currentState = state.getName();
    }
  }

  public void addTransition(String current, String nextState) {
    transitions.put(current, nextState);
  }

  public State getCurrentState() throws Exception {
    if (states.isEmpty()) {
      throw new Exception("StateMachine needs at least one state to be used.");
    } else {
      return states.stream()
          .filter(state -> state.getName().equals(currentState))
          .findFirst()
          .get();
    }
  }

  public void nextState() {
    if (states.size() > 1) {
      currentState = transitions.get(currentState);
    }
  }
}
