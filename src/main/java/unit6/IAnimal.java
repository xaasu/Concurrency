package unit6;

public interface IAnimal {
  default void breath() {
    System.out.println("breath");
  }

  default void run() {
    System.out.println("animal run");
  }
}
