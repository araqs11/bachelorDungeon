package game.level;

import de.gurkenlabs.litiengine.resources.Resources;
import java.awt.image.BufferedImage;

public abstract class Tile {

  private BufferedImage texture;

  public Tile(String imgPath) {
    this.texture = Resources.images().get(imgPath);
  }

  public BufferedImage getTexture() {
    return texture;
  }
}
