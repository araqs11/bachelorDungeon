package game.level;

import de.gurkenlabs.litiengine.resources.Resources;
import java.awt.image.BufferedImage;

public abstract class Tile {

  private BufferedImage texture;
  private boolean isWallLike;

  public Tile(String imgPath, boolean isWallLike) {
    this.texture = Resources.images().get(imgPath);
    this.isWallLike = isWallLike;
  }

  public BufferedImage getTexture() {
    return texture;
  }

  public boolean isWallLike() {
    return isWallLike;
  }
}
