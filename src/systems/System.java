package systems;

public abstract class System implements Task {

  @Override
  public Void call() {
    execute();
    return null;
  }

  /** Wrapper vor call method. */
  public abstract void execute();
}
