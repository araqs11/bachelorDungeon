package ecs.components;

import de.gurkenlabs.litiengine.resources.Resources;
import java.awt.image.BufferedImage;

public class DrawComponent extends Component {

  private BufferedImage image;

  public DrawComponent(String imagePath) {
    super();
    this.image = Resources.images().get(imagePath);
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }
}
