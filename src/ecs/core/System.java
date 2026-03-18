package ecs.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class System implements Task {

  @Override
  public Void call() {
    execute();
    return null;
  }

  /** Wrapper for call method. */
  public abstract void execute();

}
