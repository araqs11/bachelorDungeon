package util;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.util.geom.Vector2D;
import game.screens.GameLoop;

import java.awt.geom.Point2D;

public class Util {

    /**
     * Returns the position of the cursor relative to the hero.
     *
     * @return Position of the cursor in tile based coordinates
     */
    public static Vector getRelativeCursorPosition() {
        int zoomFactor = GameLoop.ZOOM * GameLoop.RENDERSCALE;
        int centerX = Game.config().graphics().getResolutionWidth()/2 - zoomFactor/2;
        int centerY = Game.config().graphics().getResolutionHeight()/2 - zoomFactor/2;
        Point2D mouse = Input.mouse().getLocation();
        double showX = (centerX-mouse.getX())/zoomFactor * -1;
        double showY = (centerY-mouse.getY())/zoomFactor * -1;
        showX = Math.floor(showX);
        showY = Math.floor(showY);
        return Vector.of(showX,showY);
    }

}
