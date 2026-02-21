package game;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

public class AudioPlayer {

    private static float volume = 0.1f;

    public static void playSound(String filename) {
        Game.audio().playSound(Resources.sounds().get(filename)).setVolume(volume);
    }

    public static void setVolume(float volume) {
        AudioPlayer.volume = volume;
    }
}
