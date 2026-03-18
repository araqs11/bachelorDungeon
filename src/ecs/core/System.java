package ecs.core;

public interface System extends Task {

  @Override
  public default Void call() {
    execute();
    return null;
  }

  /** Wrapper for call method. */
  public abstract void execute();

}
