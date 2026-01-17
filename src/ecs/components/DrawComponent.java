package ecs.components;

import de.gurkenlabs.litiengine.resources.Resources;
import game.screens.GameLoop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawComponent extends Component {

  private BufferedImage image;

  public DrawComponent(String imagePath) {
    super();
    this.image = Resources.images().get(imagePath);
  }

  public Image getScaledImage() {
    return image.getScaledInstance(image.getWidth()* GameLoop.ZOOM,image.getHeight()*GameLoop.ZOOM,1);
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }
}
