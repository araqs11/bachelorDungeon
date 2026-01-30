package game.level;

public interface ILevel {

    public void tick();
    public void onLevelLeave();
    public void onLevelLoad();
}
