package ecs.systems;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class SystemScheduler {
  ArrayList<Task> mainTask = new ArrayList<>();
  ExecutorService executorService;

  public SystemScheduler() {
    executorService = Executors.newSingleThreadExecutor();
  }

  public void schedule(Task task) {
    mainTask.add(task);
  }

  /** All scheduled systems do one execution upon calling this. */
  public void tick() {
    try {
      executorService.invokeAll(mainTask);
    } catch (InterruptedException e) {
      Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe("Error when trying to invoke all tasks");
    }
  }
}
