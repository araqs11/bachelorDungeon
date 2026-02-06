package game.level.levellogic;

public interface ILevel {

  public void tick();

  public void onLevelLeave();

  public void onLevelLoad();
}
