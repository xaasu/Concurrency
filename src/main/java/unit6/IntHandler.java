package unit6;

@FunctionalInterface
public interface IntHandler {
  void handle(int i);

  default void run() {
    System.out.println("run");
  }
}
