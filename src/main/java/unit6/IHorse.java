package unit6;

public interface IHorse {
  default void run() {
    System.out.println("horse run");
  }
}
