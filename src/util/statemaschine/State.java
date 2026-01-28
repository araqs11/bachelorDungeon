package util.statemaschine;

import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;

public class State {

  private String name;
  private Image resource;

  public State(String name, String imagePath) {
    this.name = name;
    this.resource = Resources.images().get(imagePath);
  }

  public String getName() {
    return name;
  }

  public Image getResource() {
    return resource;
  }
}
